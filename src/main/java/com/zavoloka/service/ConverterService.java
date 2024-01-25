package com.zavoloka.service;

import com.zavoloka.dto.GameDto;
import com.zavoloka.dto.QuestionDto;
import com.zavoloka.model.Game;
import com.zavoloka.model.Question;
import com.zavoloka.resolver.Resolver;

public interface ConverterService {

	Game toModel(GameDto gameDto, Resolver resolver);
	QuestionDto toDto(Question question);
}
/*Цей клас, ConverterService, є частиною проекту на Java Spring Boot і виконує ряд конвертаційних функцій між об'єктами
моделі та об'єктами DTO (Data Transfer Object). Давайте розглянемо його функції:

Метод toModel:

Призначення: Конвертує об'єкт GameDto у відповідний об'єкт моделі Game.
Вхідні параметри:
gameDto: Об'єкт типу GameDto, представляє дані гри.
resolver: Об'єкт типу Resolver, який використовується для вирішення деяких аспектів конвертації.
Вихід: Об'єкт типу Game, що представляє модель гри.
Метод toDto:

Призначення: Конвертує об'єкт Question у відповідний об'єкт QuestionDto.
Вхідні параметри:
question: Об'єкт типу Question, представляє дані питання.
Вихід: Об'єкт типу QuestionDto, що представляє об'єкт передачі даних для питання.
Отже, загальна роль ConverterService в проекті - це надання методів для зручного перетворення об'єктів між різними
представленнями (DTO та модель). Це допомагає уникнути прямого використання моделей у рівні представлення та забезпечити
чітку відокремленість між рівнями абстракції у проекті.






*/