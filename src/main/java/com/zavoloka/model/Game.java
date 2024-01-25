package com.zavoloka.model;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.zavoloka.dto.AnswerQuestionResponseDto;
import com.zavoloka.dto.AnswerStatus;
import com.zavoloka.dto.LeaderboardResponseDto;
import com.zavoloka.dto.UserAndPointsResponseDto;
import com.zavoloka.exception.GameException;
import com.zavoloka.resolver.Resolver;

public class Game {

	private final int POINTS_FOR_CORRECT_ANSWER;
	private final List<Question> questionList;
	private final Resolver resolver;
	private final Map<String, UserInGame> usernameToUserInGameMap;
	private final Object lock;

	public Game(int pointsForCorrectAnswer, List<Question> questionList, Resolver resolver) {
		super();
		POINTS_FOR_CORRECT_ANSWER = pointsForCorrectAnswer;
		this.questionList = questionList;
		this.resolver = resolver;
		usernameToUserInGameMap = new ConcurrentHashMap<>();
		lock = new Object();
	}
	
	public Optional<Question> getQuestionByUsername(String username) {

		final UserInGame userInGame = usernameToUserInGameMap.computeIfAbsent(username, k -> new UserInGame());
		
		return IntStream.range(0, questionList.size())
				 // if the user didn't answer the question and the question status is not unresolved
				 .filter(questionId ->  userInGame.getUserChoosedAnswerId(questionId) == null && 
				 						questionList.get(questionId).getQuestionStatus() != QuestionStatus.UNRESOLVED)
				 .mapToObj(questionId -> questionList.get(questionId))
				 .findFirst();
	}

	public AnswerQuestionResponseDto userAnswerQuestion(String username, int questionId, int userChoosedAnswerId) {

		Question question;
		
		try {
			question = questionList.get(questionId);
		} catch (Throwable t) {
			throw new GameException("illegal questionId: " + questionId, t);
		}
		
		if (question.getQuestionStatus() == QuestionStatus.UNRESOLVED) {
			return new AnswerQuestionResponseDto(AnswerStatus.UNRESOLVED);
		}
		
		UserInGame userInGame = usernameToUserInGameMap.get(username);
		
		if (userInGame == null) {
			throw new GameException("tried to answer a question without actually request it");
		}

		try {
			
			userInGame.getQuestionIdToUserChoosedAnswerIdMap().merge(questionId, userChoosedAnswerId, (v1, v2) -> { 
					// if the user already answered that questionId (a mapping for the key already exists)
					throw new GameException();
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
				// we should not arrive here
				throw new GameException("couldn't find the correct answer for a Resolved questionId: " + questionId);
			}
		} else if (question.getQuestionStatus() == QuestionStatus.PENDING) {
			
			synchronized (lock) {
				
				if (question.getQuestionStatus() == QuestionStatus.PENDING) {
					
					question.incTotalUserAnswered(userChoosedAnswerId);
					
					resolver.resolveAndSetStatus(question);
					
					if (question.getQuestionStatus() == QuestionStatus.RESOLVED) {
						
						Optional<Integer> correctAnswerIdOptional = question.getCorrectAnswerIdOptional();

						if (correctAnswerIdOptional.isPresent()) {
							// update points for users who previously answered correctly that questionId
							usernameToUserInGameMap.values().stream().forEach(
									userInGame2 -> userInGame2.addPointsIfAnsweredCorrect(questionId,
																correctAnswerIdOptional.get(), POINTS_FOR_CORRECT_ANSWER)
							);
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
					// we should not arrive here
					throw new GameException("couldn't find the correct answer for a Resolved questionId: " + questionId);
				}
			} else if (question.getQuestionStatus() == QuestionStatus.PENDING) {
				
				return new AnswerQuestionResponseDto(AnswerStatus.PENDING);
				
			} else if (question.getQuestionStatus() == QuestionStatus.UNRESOLVED) {
				
				return new AnswerQuestionResponseDto(AnswerStatus.UNRESOLVED);
			}
		}
		
		// we should not arrive here
		return new AnswerQuestionResponseDto(AnswerStatus.UNKNOWN);
	}
	
	public LeaderboardResponseDto getGameLeaderboard() {
		
		List<UserAndPointsResponseDto> userAndPointsList = usernameToUserInGameMap.entrySet().stream()
				.map(entry -> new UserAndPointsResponseDto(entry.getKey(), entry.getValue().getPoints()))
				.sorted(Comparator.comparingInt(UserAndPointsResponseDto::getPoints).reversed())
				.collect(Collectors.toList());
		
		return new LeaderboardResponseDto(userAndPointsList);
	}

	@Override
	public String toString() {
		return "Game [POINTS_FOR_CORRECT_ANSWER=" + POINTS_FOR_CORRECT_ANSWER + ", questionList="
				+ questionList + ", resolver=" + resolver + ", usernameToUserInGameMap=" + usernameToUserInGameMap
				+ "]";
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
/*Цей клас Game відіграє ключову роль у логіці гри в проекті. Ось його роль та функції:

Механізм гри:

Клас Game представляє собою гру в проекті. Клас ініціалізується з певною кількістю пунктів за правильну відповідь
(POINTS_FOR_CORRECT_ANSWER), списком питань (questionList) і реалізацією інтерфейсу Resolver (resolver), яка визначає
 логіку вирішення питань.
Система управління користувачами в грі:

Зберігає відображення імен користувачів на об'єкти UserInGame, що містять інформацію про бали, отримані користувачами,
 та їхні відповіді на питання.
Отримання питань для користувача:

Метод getQuestionByUsername повертає наступне питання для користувача, якщо користувач ще не відповів на нього і статус
 питання не є "нерозв'язаним".
Відповідь користувача на питання:

Метод userAnswerQuestion обробляє відповідь користувача на питання. Перевіряє, чи питання має статус "нерозв'язане",
 "очікуване" чи "розв'язане". Враховує бали за правильну відповідь.
Розв'язання питань:

Використовує реалізацію Resolver для вирішення статусу питань під час відповіді користувачів. Визначає, чи питання має
 статус "розв'язане", і враховує бали за правильні відповіді.
Лідерборд:

Метод getGameLeaderboard генерує лідерборд для гри на основі балів користувачів, сортує їх за кількістю балів у
 спадаючому порядку та повертає LeaderboardResponseDto.
Безпека від багатопоточності:

Використовує блокування (lock) для безпечного збереження та оновлення статусу питань у багатопоточному середовищі.
Клас UserInGame:

Прихований вкладений клас, який представляє інформацію про користувача в грі, його бали та відповіді на питання.
Використання стрімів та лямбда-виразів:

Використовує Java Stream API для операцій фільтрації, відображення та сортування для оптимальної обробки даних.
Інші функції:

Реалізує інші методи, такі як toString, які використовуються для представлення об'єкта у вигляді рядка.
Клас Game об'єднує всі необхідні елементи для організації гри, зберігання інформації про користувачів та взаємодію з
 питаннями та їх розв'язанням.*/