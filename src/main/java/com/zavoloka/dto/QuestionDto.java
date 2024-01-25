package com.zavoloka.dto;

import java.util.List;

public class QuestionDto {

	private Integer id;
	private String questionText;
	private List<QuestionPossibleAnswerDto> questionPossibleAnswers;
	
	public QuestionDto() {
		
	}

	public QuestionDto(String questionText) {
		super();
		this.questionText = questionText;
	}
	
	public QuestionDto(String questionText, List<QuestionPossibleAnswerDto> questionPossibleAnswers) {
		super();
		this.questionText = questionText;
		this.questionPossibleAnswers = questionPossibleAnswers;
	}

	public QuestionDto(Integer id, String questionText, List<QuestionPossibleAnswerDto> questionPossibleAnswers) {
		super();
		this.id = id;
		this.questionText = questionText;
		this.questionPossibleAnswers = questionPossibleAnswers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public List<QuestionPossibleAnswerDto> getQuestionPossibleAnswers() {
		return questionPossibleAnswers;
	}

	public void setQuestionPossibleAnswers(List<QuestionPossibleAnswerDto> questionPossibleAnswers) {
		this.questionPossibleAnswers = questionPossibleAnswers;
	}

	@Override
	public String toString() {
		return "QuestionDto [id=" + id + ", questionText=" + questionText + ", questionPossibleAnswers="
				+ questionPossibleAnswers + "]";
	}
}
/*Це клас QuestionDto використовується для передачі інформації про питання між сервером та клієнтом.
 Розглянемо його роль та функції в проекті:

Роль в проекті:

Представлення питань: Клас служить для упаковки інформації про питання, такої як ідентифікатор, текст питання та список
 можливих відповідей.
Функції класу:

Конструктори: Клас має кілька конструкторів, що дозволяє створювати об'єкти класу з різними параметрами.
Гетери та сетери: Клас надає гетери та сетери для отримання та встановлення значень полів id (ідентифікатор питання),
 questionText (текст питання) та questionPossibleAnswers (список можливих відповідей).
Перевизначений метод toString: Метод toString використовується для отримання рядкового представлення об'єкта, що може
 бути корисним при логуванні або в інших контекстах, де потрібно представити об'єкт у вигляді рядка.
Цей клас служить для передачі інформації про питання між різними частинами програми або між сервером та клієнтом,
 використовуючи об'єкт у форматі DTO.*/