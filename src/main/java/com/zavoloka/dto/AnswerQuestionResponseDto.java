package com.zavoloka.dto;

public class AnswerQuestionResponseDto {

	private AnswerStatus answerStatus;
	private int pointsEarned;

	public AnswerQuestionResponseDto(AnswerStatus answerStatus) {
		super();
		this.answerStatus = answerStatus;
		pointsEarned = 0;
	}

	public AnswerQuestionResponseDto(AnswerStatus answerStatus, int pointsEarned) {
		super();
		this.answerStatus = answerStatus;
		this.pointsEarned = pointsEarned;
	}

	public AnswerStatus getAnswerStatus() {
		return answerStatus;
	}

	public int getPointsEarned() {
		return pointsEarned;
	}

	@Override
	public String toString() {
		return "AnswerQuestionResponseDto [answerStatus=" + answerStatus + ", pointsEarned=" + pointsEarned + "]";
	}
}
/*Це клас AnswerQuestionResponseDto, і ось його роль та функції в проекті:

Роль в проекті:

Відповідь гравця: Представляє відповідь гравця на питання в рамках гри, включаючи статус відповіді та кількість отриманих балів.
Функції класу:

Зберігання статусу відповіді: Утримує інформацію про статус відповіді, яка представлена за допомогою перерахування AnswerStatus.
Кількість отриманих балів: Зберігає кількість балів, які гравець отримав за відповідь на питання.
Конструктори: Надає конструктори для створення об'єктів з різними комбінаціями статусу відповіді та кількості отриманих балів.
Методи доступу: Забезпечує методи доступу до статусу відповіді та кількості отриманих балів.
Перевизначення toString: Надає рядкове представлення об'єкта для зручного виведення у логах чи інших вихідних потоках.
Цей клас є частиною системи для обробки відповідей гравців та управління грою під час виконання розіграшу.*/