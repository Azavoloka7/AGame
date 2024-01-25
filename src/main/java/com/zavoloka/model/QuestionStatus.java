package com.zavoloka.model;

public enum QuestionStatus {

	RESOLVED("Resolved"),
	PENDING("Pending"),
	UNRESOLVED("Unresolved");

	private String status;

	QuestionStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return status;
	}
}
/*Це перерахування (enum) "QuestionStatus" використовується для представлення статусу питань у грі. Розглянемо його
 роль та функції в проекті:

Роль в проекті:

Представлення можливих статусів, які може мати питання під час гри.
Функції перерахування:

Статуси питань: Містить три можливих статуси для питань у грі - "RESOLVED" (вирішено), "PENDING" (очікує вирішення) та
 "UNRESOLVED" (не вирішено).
Конструктор: Ініціалізує перерахування із заданим текстовим значенням статусу.
toString: Перевизначений метод для отримання текстового представлення статусу.
Це перерахування є зручним для використання в коді для представлення стану питань у грі. Він допомагає зробити код
 більш читабельним і ефективним при роботі з різними станами питань.*/