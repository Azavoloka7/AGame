package com.zavoloka.model;

import com.zavoloka.dto.AnswerQuestionResponseDto;
import com.zavoloka.dto.AnswerStatus;
import com.zavoloka.dto.LeaderboardResponseDto;
import com.zavoloka.dto.UserAndPointsResponseDto;
import com.zavoloka.exception.GameException;
import com.zavoloka.resolver.Resolver;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {

    private final int POINTS_FOR_CORRECT_ANSWER;
    private final List<Question> questionList;
    private final Resolver resolver;
    private final Map<String, UserInGame> usernameToUserInGameMap;
    private final Object lock = new Object();

    public Game(int pointsForCorrectAnswer, List<Question> questionList, Resolver resolver) {
        POINTS_FOR_CORRECT_ANSWER = pointsForCorrectAnswer;
        this.questionList = questionList;
        this.resolver = resolver;
        this.usernameToUserInGameMap = new ConcurrentHashMap<>();
    }

    public Optional<Question> getQuestionByUsername(String username) {
        final UserInGame userInGame = usernameToUserInGameMap.computeIfAbsent(username, k -> new UserInGame());
        return IntStream.range(0, questionList.size())
                .filter(questionId -> userInGame.getUserChoosedAnswerId(questionId) == null &&
                        questionList.get(questionId).getQuestionStatus() != QuestionStatus.UNRESOLVED)
                .mapToObj(questionList::get)
                .findFirst();
    }

    public AnswerQuestionResponseDto userAnswerQuestion(String username, int questionId, int userChoosedAnswerId) {
        Question question = getQuestion(questionId);

        if (question.getQuestionStatus() == QuestionStatus.UNRESOLVED) {
            return new AnswerQuestionResponseDto(AnswerStatus.UNRESOLVED);
        }

        UserInGame userInGame = getUserInGame(username);

        if (userInGame == null) {
            throw new GameException("Tried to answer a question without actually requesting it");
        }

        try {
            userInGame.getQuestionIdToUserChoosedAnswerIdMap().merge(questionId, userChoosedAnswerId,
                    (v1, v2) -> {
                        throw new GameException("User already answered this question");
                    }
            );
        } catch (GameException ge) {
            return new AnswerQuestionResponseDto(AnswerStatus.ALREADY_ANSWERED);
        }

        if (question.getQuestionStatus() == QuestionStatus.RESOLVED) {
            Optional<Integer> correctAnswerIdOptional = question.getCorrectAnswerIdOptional();

            if (correctAnswerIdOptional.isPresent()) {
                if (userInGame.addPointsIfAnsweredCorrect(questionId, correctAnswerIdOptional.get(), POINTS_FOR_CORRECT_ANSWER)) {
                    return new AnswerQuestionResponseDto(AnswerStatus.CORRECT, POINTS_FOR_CORRECT_ANSWER);
                } else {
                    return new AnswerQuestionResponseDto(AnswerStatus.WRONG);
                }
            } else {
                throw new GameException("Couldn't find the correct answer for a Resolved questionId: " + questionId);
            }
        } else if (question.getQuestionStatus() == QuestionStatus.PENDING) {
            synchronized (lock) {
                if (question.getQuestionStatus() == QuestionStatus.PENDING) {
                    question.incTotalUserAnswered(userChoosedAnswerId);
                    resolver.resolveAndSetStatus(question);

                    if (question.getQuestionStatus() == QuestionStatus.RESOLVED) {
                        Optional<Integer> correctAnswerIdOptional = question.getCorrectAnswerIdOptional();

                        if (correctAnswerIdOptional.isPresent()) {
                            usernameToUserInGameMap.values().forEach(userInGame2 ->
                                    userInGame2.addPointsIfAnsweredCorrect(questionId, correctAnswerIdOptional.get(), POINTS_FOR_CORRECT_ANSWER));
                        }
                    }
                }
            }

            if (question.getQuestionStatus() == QuestionStatus.RESOLVED) {
                Optional<Integer> correctAnswerIdOptional = question.getCorrectAnswerIdOptional();

                if (correctAnswerIdOptional.isPresent()) {
                    if (userChoosedAnswerId == correctAnswerIdOptional.get()) {
                        return new AnswerQuestionResponseDto(AnswerStatus.CORRECT, POINTS_FOR_CORRECT_ANSWER);
                    } else {
                        return new AnswerQuestionResponseDto(AnswerStatus.WRONG);
                    }
                } else {
                    throw new GameException("Couldn't find the correct answer for a Resolved questionId: " + questionId);
                }
            } else if (question.getQuestionStatus() == QuestionStatus.PENDING) {
                return new AnswerQuestionResponseDto(AnswerStatus.PENDING);
            } else if (question.getQuestionStatus() == QuestionStatus.UNRESOLVED) {
                return new AnswerQuestionResponseDto(AnswerStatus.UNRESOLVED);
            }
        }

        return new AnswerQuestionResponseDto(AnswerStatus.UNKNOWN);
    }

    public LeaderboardResponseDto getGameLeaderboard() {
        List<UserAndPointsResponseDto> userAndPointsList = usernameToUserInGameMap.entrySet().stream()
                .map(entry -> new UserAndPointsResponseDto(entry.getKey(), entry.getValue().getPoints()))
                .sorted(Comparator.comparingInt(UserAndPointsResponseDto::getPoints).reversed())
                .collect(Collectors.toList());

        return new LeaderboardResponseDto(userAndPointsList);
    }

    private Question getQuestion(int questionId) {
        try {
            return questionList.get(questionId);
        } catch (IndexOutOfBoundsException e) {
            throw new GameException("Illegal questionId: " + questionId, e);
        }
    }

    private UserInGame getUserInGame(String username) {
        return usernameToUserInGameMap.get(username);
    }

    @Override
    public String toString() {
        return "Game [POINTS_FOR_CORRECT_ANSWER=" + POINTS_FOR_CORRECT_ANSWER + ", questionList=" + questionList
                + ", resolver=" + resolver + ", usernameToUserInGameMap=" + usernameToUserInGameMap + "]";
    }

    private static class UserInGame {

        private AtomicInteger points;
        private Map<Integer, Integer> questionIdToUserChoosedAnswerIdMap;

        private UserInGame() {
            points = new AtomicInteger(0);
            questionIdToUserChoosedAnswerIdMap = new ConcurrentHashMap<>();
        }

        private int getPoints() {
            return points.get();
        }

        private boolean addPointsIfAnsweredCorrect(int questionId, int correctAnswerId, int pointsForCorrectAnswer) {
            int userChoosedAnswerId = questionIdToUserChoosedAnswerIdMap.get(questionId);

            if (userChoosedAnswerId == correctAnswerId) {
                points.addAndGet(pointsForCorrectAnswer);
                return true;
            }

            return false;
        }

        private Integer getUserChoosedAnswerId(int questionId) {
            return questionIdToUserChoosedAnswerIdMap.get(questionId);
        }

        public Map<Integer, Integer> getQuestionIdToUserChoosedAnswerIdMap() {
            return questionIdToUserChoosedAnswerIdMap;
        }
    }
}
