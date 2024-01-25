package com.zavoloka.model;

import java.util.concurrent.atomic.AtomicInteger;

public class QuestionPossibleAnswer {

	private int id;
	private String possibleAnswer;
	private AtomicInteger totalUserAnswered;
	
	public QuestionPossibleAnswer(int id, String possibleAnswer) {
		super();
		this.id = id;
		this.possibleAnswer = possibleAnswer;
		totalUserAnswered = new AtomicInteger(0);
	}
	
	public int getId() {
		return id;
	}
	
	public void incTotalUserAnswered() {
		
		totalUserAnswered.incrementAndGet();
	}
	
	public int getTotalUserAnswered() {
		
		return totalUserAnswered.get();
	}

	public String getPossibleAnswer() {

		return possibleAnswer;
	}

	@Override
	public String toString() {
		return "QuestionPossibleAnswer [id=" + id + ", possibleAnswer=" + possibleAnswer + ", totalUserAnswered="
				+ totalUserAnswered + "]";
	}
}

/*
Цей клас "QuestionPossibleAnswer" використовується для представлення можливих відповідей на питання у грі. Розглянемо
 його роль та функції в проекті:

Роль в проекті:

Представлення конкретної можливої відповіді на питання в грі.
Функції класу:

Конструктор: Ініціалізує об'єкт можливої відповіді із заданим ідентифікатором та текстом можливої відповіді.
getters: Надає методи доступу до властивостей можливої відповіді, таких як ідентифікатор, текст відповіді, кількість
 користувачів, які обрали цю відповідь.
incTotalUserAnswered: Збільшує лічильник користувачів, які обрали цю відповідь на питання.
toString: Перевизначений метод для генерації рядкового представлення об'єкта, що корисно для логування та налагодження.
Цей клас важливий для представлення варіантів відповідей та відстеження кількості користувачів, які вибрали кожну з цих
 відповідей.*/