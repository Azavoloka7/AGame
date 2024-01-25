package com.zavoloka.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zavoloka.dto.GameExceptionResponseDto;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionInterceptor.class);

	@ExceptionHandler(GameException.class)
	public final ResponseEntity<GameExceptionResponseDto> handleAllExceptions(GameException gx) {

		LOGGER.error(gx.getMessage(), gx);

		GameExceptionResponseDto exceptionResponse = new GameExceptionResponseDto(gx.getMessageResponse());

		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
/*
Це клас ExceptionInterceptor, який використовується для обробки винятків у вашому проекті. Розглянемо його роль та
 функції в проекті:

Роль в проекті:

Обробник винятків: Використовується для обробки винятків, які можуть виникнути в процесі виконання вашого додатку.
Централізована обробка винятків: @ControllerAdvice дозволяє централізовано обробляти винятки для всіх контролерів у
вашому додатку.
Функції класу:

handleAllExceptions: Анотація @ExceptionHandler(GameException.class) вказує, що цей метод обробляє винятки типу
GameException. При виникненні такого винятку викликається цей метод.
Логування: Записує виняток та повідомлення про помилку до журналу (логу) за допомогою SLF4J та Logger.
Створення відповіді: Створює відповідь ResponseEntity з об'єктом GameExceptionResponseDto, який містить інформацію про
помилку.
HTTP статус: Встановлює HTTP статус INTERNAL_SERVER_ERROR для відповіді, оскільки це виняткова ситуація.
Цей клас дозволяє вам забезпечити централізовану обробку помилок у вашому додатку та повертати коректні відповіді у
випадку виняткових ситуацій.*/