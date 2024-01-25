package com.zavoloka.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AnswerStatus {

	CORRECT("Correct"),
	WRONG("Wrong"),
	PENDING("Pending"),
	UNRESOLVED("Unresolved"),
	ALREADY_ANSWERED("Already Answered"),
	UNKNOWN("Unknown");

	private String status;

	AnswerStatus(String status) {
		this.status = status;
	}

	@JsonValue
	@Override
	public String toString() {
		return status;
	}
}
/*Це перерахування AnswerStatus, і ось його роль та функції в проекті:

Роль в проекті:

Статуси відповіді: Визначає різні можливі статуси відповіді гравця на питання під час гри.

Функції перерахування:

Представлення статусу: Кожне значення перерахування представляє конкретний статус відповіді. Наприклад, "CORRECT"
вказує на правильну відповідь, "WRONG" - на неправильну, і так далі.
Зручний метод toString: Використовується анотація @JsonValue над перерахуванням, щоб забезпечити виведення в рядок,
яке буде використовуватися при серіалізації об'єктів в формат JSON. Це робить об'єкти зрозумілими для інших систем,
які взаємодіють з цими даними через API.
Це перерахування допомагає чітко визначити статус відповіді та спрощує обробку цих статусів у коді гри.*/