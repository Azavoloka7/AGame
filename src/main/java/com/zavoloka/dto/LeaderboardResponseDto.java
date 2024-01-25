package com.zavoloka.dto;

import java.util.List;

public class LeaderboardResponseDto {

	private List<UserAndPointsResponseDto> userAndPointsPairs;

	public LeaderboardResponseDto(List<UserAndPointsResponseDto> userAndPointsPairs) {
		super();
		this.userAndPointsPairs = userAndPointsPairs;
	}

	public List<UserAndPointsResponseDto> getUserAndPointsPairs() {
		return userAndPointsPairs;
	}

	public void setUserAndPointsPairs(List<UserAndPointsResponseDto> userAndPointsPairs) {
		this.userAndPointsPairs = userAndPointsPairs;
	}

	@Override
	public String toString() {
		return "LeaderboardResponseDto [userAndPointsPairs=" + userAndPointsPairs + "]";
	}
}
/*Це клас LeaderboardResponseDto, який, ймовірно, використовується для передачі інформації про лідерборд між сервером
та клієнтом. Розглянемо його роль та функції в проекті:

Роль в проекті:

Представлення лідерборду: Клас служить для упаковки інформації про лідерборд, а саме список пар "користувач-бали".

Функції класу:

Конструктор: Клас має конструктор, який дозволяє створювати об'єкти класу з переданим списком пар "користувач-бали".

Гетер та сетер для списку: Забезпечує гетер та сетер для отримання та встановлення значень для списку пар "користувач-бали".
Перевизначений метод toString: Метод toString використовується для отримання рядкового представлення об'єкта, що може
бути корисним при логуванні або в інших контекстах, де потрібно представити об'єкт у вигляді рядка.
Цей клас використовується для передачі інформації про лідерборд між різними частинами програми або між сервером та
клієнтом, використовуючи об'єкт у форматі DTO.*/