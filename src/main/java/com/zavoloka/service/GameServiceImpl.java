package com.zavoloka.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zavoloka.dto.AnswerQuestionRequestDto;
import com.zavoloka.dto.AnswerQuestionResponseDto;
import com.zavoloka.dto.GameDto;
import com.zavoloka.dto.LeaderboardResponseDto;
import com.zavoloka.dto.QuestionDto;
import com.zavoloka.exception.GameException;
import com.zavoloka.model.Game;
import com.zavoloka.model.Question;
import com.zavoloka.repository.GameRepository;
import com.zavoloka.resolver.Resolver;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ConverterService converterService;
    private final Resolver resolver;

    public GameServiceImpl(GameRepository gameRepository, ConverterService converterService, Resolver resolver) {
        this.gameRepository = gameRepository;
        this.converterService = converterService;
        this.resolver = resolver;
    }

    @Override
    public int save(GameDto gameDto) {
        Game game = converterService.toModel(gameDto, resolver);
        return gameRepository.save(game);
    }

    @Override
    public QuestionDto getQuestionByUsername(int gameId, String username) {
        Game game = gameRepository.getById(gameId);
        Optional<Question> optionalQuestion = game.getQuestionByUsername(username);

        return optionalQuestion.map(converterService::toDto)
                .orElseThrow(() -> new GameException("No available questions for user " + username + " in game id " + gameId,
                        "No available questions for this game. Please try another game."));
    }

    @Override
    public AnswerQuestionResponseDto userAnswerQuestion(int gameId, String username,
                                                        AnswerQuestionRequestDto answerQuestionRequestDto) {
        Integer questionId = answerQuestionRequestDto.getQuestionId();
        if (questionId == null) {
            throw new GameException("field questionId can't be null");
        }

        Integer userChoosedAnswerId = answerQuestionRequestDto.getUserChoosedAnswerId();
        if (userChoosedAnswerId == null) {
            throw new GameException("field userChoosedAnswerId can't be null");
        }

        Game game = gameRepository.getById(gameId);
        return game.userAnswerQuestion(username, questionId, userChoosedAnswerId);
    }

    @Override
    public LeaderboardResponseDto getLeaderboard(int gameId) {
        Game game = gameRepository.getById(gameId);
        return game.getGameLeaderboard();
    }
}
/*GameServiceImpl - це сервісний клас, який реалізує логіку взаємодії з грою в рамках проекту. Основна його роль -
це обробка та виконання різноманітних операцій пов'язаних із грою на вищому рівні абстракції. Давайте розглянемо ключові елементи та їх функції:

Поля класу:

gameRepository: Використовується для взаємодії з репозиторієм (GameRepository), де зберігається інформація про гру.
converterService: Сервіс для конвертації об'єктів між моделями (наприклад, Game) та DTO (Data Transfer Objects).
resolver: Використовується для вирішення питань, пов'язаних з грою (наприклад, вибір поточного питання).
Конструктор:

Ініціалізує поля класу, використовуючи передані залежності.
Метод save(GameDto gameDto):

Зберігає гру в репозиторії. Конвертує об'єкт GameDto у модель Game за допомогою converterService та resolver.
Метод getQuestionByUsername(int gameId, String username):

Отримує питання для користувача зазначеної гри за його ідентифікатором та ім'ям користувача.
Використовує сервіс конвертації для перетворення об'єкта Question у відповідний DTO (QuestionDto).
Метод userAnswerQuestion(int gameId, String username, AnswerQuestionRequestDto answerQuestionRequestDto):

Обробляє відповідь користувача на питання у зазначеній грі та із зазначеним іменем користувача.
Перевіряє наявність обов'язкових полів у запиті та викликає метод userAnswerQuestion об'єкта Game.
Повертає DTO з результатом відповіді користувача на питання.
Метод getLeaderboard(int gameId):

Отримує лідерборд (рейтинг гравців) для гри за зазначеним ідентифікатором гри.
Використовує метод getGameLeaderboard об'єкта Game.
Цей клас служить як проміжний рівень між контролерами та репозиторієм, реалізуючи бізнес-логіку гри та надаючи зручний
інтерфейс для взаємодії з грою на рівні сервісу.*/