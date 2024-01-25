package com.zavoloka.model;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.zavoloka.exception.GameException;

public class Question {

	private int id;
	private String questionText;
	private List<QuestionPossibleAnswer> questionPossibleAnswerList;
	private Optional<Integer> correctAnswerIdOptional;
	private QuestionStatus questionStatus;
	private AtomicInteger totalUserAnswered;

	public Question(int id, String questionText, List<QuestionPossibleAnswer> questionPossibleAnswerList) {
		super();
		this.id = id;
		this.questionText = questionText;
		this.questionPossibleAnswerList = questionPossibleAnswerList;
		correctAnswerIdOptional = Optional.empty();
		questionStatus = QuestionStatus.PENDING;
		totalUserAnswered = new AtomicInteger(0);
	}

	public int getId() {
		return id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public List<QuestionPossibleAnswer> getQuestionPossibleAnswerList() {
		return questionPossibleAnswerList;
	}

	public Optional<Integer> getCorrectAnswerIdOptional() {
		return correctAnswerIdOptional;
	}

	public void setCorrectAnswerIdOptional(Optional<Integer> correctAnswerIdOptional) {
		this.correctAnswerIdOptional = correctAnswerIdOptional;
	}

	public QuestionStatus getQuestionStatus() {
		return questionStatus;
	}

	public void setQuestionStatus(QuestionStatus questionStatus) {
		this.questionStatus = questionStatus;
	}
	
	public int getTotalUserAnswered() {
		
		return totalUserAnswered.get();
	}
	
	public void incTotalUserAnswered(int questionPossibleAnswerId) {

		totalUserAnswered.incrementAndGet();
		
		QuestionPossibleAnswer questionPossibleAnswer;
		
		try {
			questionPossibleAnswer = questionPossibleAnswerList.get(questionPossibleAnswerId);
		} catch (Throwable t) {
			throw new GameException("illegal questionPossibleAnswerId: " + questionPossibleAnswerId, t);
		}
		
		questionPossibleAnswer.incTotalUserAnswered();
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionText=" + questionText + ", questionPossibleAnswerList="
				+ questionPossibleAnswerList + ", correctAnswerIdOptional=" + correctAnswerIdOptional
				+ ", questionStatus=" + questionStatus + ", totalUserAnswered=" + totalUserAnswered + "]";
	}
}
/*
Цей клас "Question" представляє питання в грі. Давайте розглянемо його роль та функції в проекті:

Роль в проекті:

Представлення конкретного питання в грі.
Функції класу:

Конструктор: Ініціалізує об'єкт питання із заданим ідентифікатором, текстом питання та списком можливих відповідей.
getters і setters: Надає методи доступу до властивостей питання, таких як ідентифікатор, текст, список можливих
 відповідей, статус питання і т. д.
incTotalUserAnswered: Збільшує лічильник загальної кількості користувачів, які відповіли на це питання.
setCorrectAnswerIdOptional: Встановлює правильну відповідь для цього питання.
setQuestionStatus: Встановлює статус питання (очікування, вирішене, невирішене).
toString: Перевизначений метод для генерації рядкового представлення об'єкта, що корисно для логування та налагодження.
Цей клас важливий для представлення питань у грі та взаємодії з ними, а також для зберігання статистики про
користувачів, які відповіли на кожне питання.






*/