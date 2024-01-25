package com.zavoloka.dto;

public class AnswerQuestionRequestDto {

	private Integer questionId;
	private Integer userChoosedAnswerId;

	public AnswerQuestionRequestDto() {
		
	}

	public AnswerQuestionRequestDto(Integer questionId, Integer userChoosedAnswerId) {
		super();
		this.questionId = questionId;
		this.userChoosedAnswerId = userChoosedAnswerId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getUserChoosedAnswerId() {
		return userChoosedAnswerId;
	}

	public void setUserChoosedAnswerId(Integer userChoosedAnswerId) {
		this.userChoosedAnswerId = userChoosedAnswerId;
	}

	@Override
	public String toString() {
		return "AnswerQuestionRequestDto [questionId=" + questionId + ", userChoosedAnswerId=" + userChoosedAnswerId + "]";
	}
}
/*Це клас AnswerQuestionRequestDto, і ось його роль та функції в проекті:

Роль в проекті:

Запит на відповідь гравця: Представляє запит на відповідь гравця на конкретне питання в рамках гри.
Функції класу:

Зберігання інформації про відповідь: Утримує інформацію про ідентифікатор питання (questionId), на яке гравець надає
відповідь, і ідентифікатор вибраної гравцем відповіді (userChoosedAnswerId).
Конструктори: Надає конструктори для створення об'єктів з різними комбінаціями ідентифікаторів питання та вибраної відповіді.
Методи доступу: Забезпечує методи доступу до ідентифікатора питання та ідентифікатора вибраної гравцем відповіді.
Перевизначення toString: Надає рядкове представлення об'єкта для зручного виведення у логах чи інших вихідних потоках.
Цей клас використовується для передачі інформації від гравців до системи гри щодо їхніх відповідей на конкретні питання.






*/