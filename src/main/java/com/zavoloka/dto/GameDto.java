package com.zavoloka.dto;

import java.util.List;

public class GameDto {

	private Integer id;
	private Integer pointsForCorrectAnswer;
	private List<QuestionDto> questions;
	
	public GameDto() {
		
	}
	
	public GameDto(Integer pointsForCorrectAnswer, List<QuestionDto> questions) {
		super();
		this.pointsForCorrectAnswer = pointsForCorrectAnswer;
		this.questions = questions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPointsForCorrectAnswer() {
		return pointsForCorrectAnswer;
	}

	public void setPointsForCorrectAnswer(Integer pointsForCorrectAnswer) {
		this.pointsForCorrectAnswer = pointsForCorrectAnswer;
	}

	public List<QuestionDto> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionDto> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "GameDto [id=" + id + ", pointsForCorrectAnswer=" + pointsForCorrectAnswer + ", questions=" + questions
				+ "]";
	}
}
/*Це клас GameDto, і ось його роль та функції в проекті:

Роль в проекті:

Представлення гри: Клас використовується для представлення гри (ігрового контенту та налаштувань) в об'єкті формату DTO,
який може передаватися між компонентами системи, зокрема, між клієнтом та сервером.

Функції класу:

Конструктори: Клас має конструктори для створення об'єктів класу з різними параметрами.
Гетери та сетери: Забезпечує гетери та сетери для доступу до полів об'єкта, щоб інші частини коду могли звертатися до
інформації про гру.
Переоприлюднений метод toString: Переоприлюднює метод toString, щоб забезпечити зручне рядкове представлення об'єкта
для виведення у логах або інших відладочних цілях.
Цей клас слугує як обгортка для інформації про гру і використовується для обміну цією інформацією між різними частинами
програми.*/