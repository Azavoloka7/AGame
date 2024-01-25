package com.zavoloka.dto;

public class UserAndPointsResponseDto {

	private String username;
	private Integer points;
	
	public UserAndPointsResponseDto(String username, Integer points) {
		super();
		this.username = username;
		this.points = points;
	}

	public String getUsername() {
		return username;
	}

	public Integer getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return "UserAndPointsResponseDto [username=" + username + ", points=" + points + "]";
	}
}
/*Це клас UserAndPointsResponseDto, який використовується для представлення інформації про користувача та
 його кількість балів в лідерборді. Розглянемо його роль та функції в проекті:

Роль в проекті:

Представлення інформації про користувача та його бали: Клас служить для упаковки інформації про користувача (ім'я
 користувача) та його кількість балів у лідерборді.

Функції класу:

Конструктор: Клас має конструктор, який приймає ім'я користувача та кількість балів при створенні об'єкта.
Гетери для полів: Клас надає гетери для отримання значень полів username (ім'я користувача) та points (кількість балів).
Перевизначений метод toString: Метод toString використовується для отримання рядкового представлення об'єкта, що може
 бути корисним при логуванні або в інших контекстах, де потрібно представити об'єкт у вигляді рядка.
Цей клас допомагає структурувати та передавати інформацію про користувача та його результати в лідерборді через DTO
 (Data Transfer Object).
*/