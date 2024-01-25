package com.zavoloka.exception;

public class GameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4744858762248583172L;

	private String messageResponse;

	public GameException() {

	}

	public GameException(String message) {
		super(message);
		this.messageResponse = message;
	}

	public GameException(Throwable cause) {
		super(cause);
	}

	public GameException(String message, Throwable cause) {
		super(message, cause);
		this.messageResponse = message;
	}

	public GameException(String message, String messageResponse) {
		super(message);
		this.messageResponse = messageResponse;
	}
	
	public GameException(String message, String messageResponse, Throwable cause) {
		super(message, cause);
		this.messageResponse = messageResponse;
	}
/*
	public GameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}*/

	public String getMessageResponse() {
		return messageResponse;
	}
}
/*
Це клас GameException, який розширює RuntimeException і використовується для представлення виняткових ситуацій в
додатку. Розглянемо його роль та функції в проекті:

Роль в проекті:

Представлення виняткових ситуацій: Використовується для створення та представлення власних виняткових ситуацій в коді
програми.
Спадковість від RuntimeException: Тим, що цей клас є підкласом RuntimeException, вказує на те, що це є неперевіреним
винятком, і вам не потрібно вказувати його в сигнатурі методів або обробляти його в блоках try-catch, якщо
необов'язково.

Функції класу:

messageResponse: Поля messageResponse використовується для зберігання додаткового повідомлення про помилку, яке може
використовуватися для формування відповіді користувачеві або логування.
Конструктори: Клас має декілька конструкторів для створення об'єктів GameException з різними параметрами, що дозволяє
передавати повідомлення про помилку, додаткове повідомлення та інші параметри при створенні винятку.
Метод getMessageResponse: Дозволяє отримати значення поля messageResponse, яке може бути використане для отримання
інформації про помилку для відповіді користувачеві чи логування.
Цей клас є важливим компонентом для обробки виняткових ситуацій в вашому додатку та надає можливість деталізованої
 обробки помилок у вашому коді.*/