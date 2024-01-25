package com.zavoloka.dto;

public class QuestionPossibleAnswerDto {

	private Integer id;
	private String possibleAnswer;
	
	public QuestionPossibleAnswerDto() {
		
	}

	public QuestionPossibleAnswerDto(Integer id, String possibleAnswer) {
		super();
		this.id = id;
		this.possibleAnswer = possibleAnswer;
	}

	public QuestionPossibleAnswerDto(String possibleAnswer) {
		super();
		this.possibleAnswer = possibleAnswer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPossibleAnswer() {
		return possibleAnswer;
	}

	public void setPossibleAnswer(String possibleAnswer) {
		this.possibleAnswer = possibleAnswer;
	}

	@Override
	public String toString() {
		return "QuestionPossibleAnswerDto [id=" + id + ", possibleAnswer=" + possibleAnswer + "]";
	}
}
/*
Це клас QuestionPossibleAnswerDto, який використовується для представлення можливих відповідей на питання у форматі,
який можна передати між клієнтом та сервером. Розглянемо його роль та функції в проекті:

Роль в проекті:

Представлення можливих відповідей: Клас служить для упаковки інформації про можливі відповіді на питання, такі як їх
ідентифікатор та текст.

Функції класу:

Конструктори: Клас має кілька конструкторів, що дозволяє створювати об'єкти класу з різними параметрами.
Гетери та сетери: Клас надає гетери та сетери для отримання та встановлення значень полів id (ідентифікатор відповіді)
та possibleAnswer (текст відповіді).
Перевизначений метод toString: Метод toString використовується для отримання рядкового представлення об'єкта, що може
бути корисним при логуванні або в інших контекстах, де потрібно представити об'єкт у вигляді рядка.
Цей клас допомагає передавати інформацію про можливі відповіді між різними частинами програми або між сервером та
клієнтом, використовуючи об'єкт у форматі DTO.*/