package com.zavoloka.service;

import com.zavoloka.dto.AnswerQuestionRequestDto;
import com.zavoloka.dto.AnswerQuestionResponseDto;
import com.zavoloka.dto.GameDto;
import com.zavoloka.dto.LeaderboardResponseDto;
import com.zavoloka.dto.QuestionDto;

public interface GameService {

	int save(GameDto gameDto);
	QuestionDto getQuestionByUsername(int gameId, String username);
	AnswerQuestionResponseDto userAnswerQuestion(int gameId, String username, AnswerQuestionRequestDto answerQuestionRequestDto);
	LeaderboardResponseDto getLeaderboard(int gameId);
}
/*Цей інтерфейс, GameService, визначає контракт для сервісу, який надає ряд функцій, пов'язаних із грою. Давайте
розглянемо кожен метод окремо:

Метод save:

Призначення: Зберігає гру, отриману у вигляді GameDto.
Вхідні параметри: Об'єкт GameDto, який представляє гру для збереження.
Вихід: Ціле число, яке ідентифікує збережену гру.
Метод getQuestionByUsername:

Призначення: Отримує питання для користувача у вказаній грі та зазначеного користувача.
Вхідні параметри:
gameId: Ідентифікатор гри.
username: Ім'я користувача.
Вихід: Об'єкт QuestionDto, який представляє питання для користувача.
Метод userAnswerQuestion:

Призначення: Обробляє відповідь користувача на питання у вказаній грі та зазначеного користувача.
Вхідні параметри:
gameId: Ідентифікатор гри.
username: Ім'я користувача.
answerQuestionRequestDto: Об'єкт, що містить інформацію про відповідь користувача.
Вихід: Об'єкт AnswerQuestionResponseDto, який містить результат обробки відповіді.
Метод getLeaderboard:

Призначення: Отримує таблицю лідерів для вказаної гри.
Вхідні параметри:
gameId: Ідентифікатор гри.
Вихід: Об'єкт LeaderboardResponseDto, який містить інформацію про таблицю лідерів.
Цей інтерфейс описує важливі функції, які можуть бути використані для взаємодії з грою в контексті додатка або сервісу,
побудованого на Java Spring Boot. Реалізація цих методів буде відповідальністю конкретного класу, що реалізує цей інтерфейс.*/