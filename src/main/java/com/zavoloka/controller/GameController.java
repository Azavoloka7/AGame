package com.zavoloka.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zavoloka.dto.AnswerQuestionRequestDto;
import com.zavoloka.dto.AnswerQuestionResponseDto;
import com.zavoloka.dto.GameDto;
import com.zavoloka.dto.LeaderboardResponseDto;
import com.zavoloka.dto.QuestionDto;
import com.zavoloka.service.GameService;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

	@Autowired
	private GameService gameService;

	@PostMapping("/")
	public ResponseEntity<Void> createGame(@RequestBody GameDto gameDto) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("gameDto={}", gameDto);
		}

		int gameId = gameService.save(gameDto);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("gameId={}", gameId);
		}
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(gameId)
                .toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/{gameId}/username/{username}")
	public QuestionDto getQuestionDto(@PathVariable("gameId") final int gameId,
			@PathVariable("username") final String username) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("gameId={}, username={}", gameId, username);
		}

		QuestionDto questionDto = gameService.getQuestionByUsername(gameId, username);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("questionDto={}", questionDto);
		}

		return questionDto;
	}

	@PostMapping("/{gameId}/username/{username}")
	@ResponseStatus(HttpStatus.CREATED)
	public AnswerQuestionResponseDto answerQuestion(@PathVariable("gameId") final int gameId,
			@PathVariable("username") final String username,
			@RequestBody final AnswerQuestionRequestDto answerQuestionRequestDto) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("gameId={}, username={}, answerQuestionRequestDto={}", gameId, username, answerQuestionRequestDto);
		}

		AnswerQuestionResponseDto answerQuestionResponseDto = gameService.userAnswerQuestion(gameId, username, answerQuestionRequestDto);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("answerQuestionResponseDto={}", answerQuestionResponseDto);
		}

		return answerQuestionResponseDto;
	}

	@GetMapping("/{gameId}/leaderboard")
	public LeaderboardResponseDto getLeaderboard(@PathVariable("gameId") final int gameId) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("gameId={}", gameId);
		}

		LeaderboardResponseDto leaderboardResponseDto = gameService.getLeaderboard(gameId);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("leaderboardResponseDto={}", leaderboardResponseDto);
		}

		return leaderboardResponseDto;
	}
}
/*
Це клас GameController, і ось його роль та функції в проекті:

Роль в проекті:

Контролер гри: Відповідає за обробку HTTP-запитань, пов'язаних із грою, та взаємодію з сервісним шаром.
Функції класу:

Створення гри: Обробляє HTTP POST-запит для створення нової гри на основі наданого GameDto. Викликає метод сервісу для збереження гри та повертає статус відповіді з URI для новоствореної гри.
Отримання питання для гравця: Обробляє HTTP GET-запит для отримання наступного питання для конкретного гравця у рамках визначеної гри. Викликає метод сервісу для отримання QuestionDto.
Відповідь гравця на питання: Обробляє HTTP POST-запит для обробки відповіді гравця на питання. Викликає метод сервісу для обробки відповіді та повертає AnswerQuestionResponseDto.
Отримання інформації про лідерів гри: Обробляє HTTP GET-запит для отримання інформації про лідерів гри. Викликає метод сервісу для отримання LeaderboardResponseDto.
Інші особливості:

Журналювання: Використовує SLF4J для журналювання подій.
Використання сервісного шару: Автоматично взаємодіє із сервісним шаром (в даному випадку, класом GameService), викликаючи його методи для виконання бізнес-логіки.
Цей клас є точкою входу для обробки запитів, пов'язаних із грою, у веб-додатку.*/