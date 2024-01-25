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
        this.id = id;
        this.questionText = questionText;
        this.questionPossibleAnswerList = questionPossibleAnswerList;
        this.correctAnswerIdOptional = Optional.empty();
        this.questionStatus = QuestionStatus.PENDING;
        this.totalUserAnswered = new AtomicInteger(0);
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

        try {
            QuestionPossibleAnswer questionPossibleAnswer = questionPossibleAnswerList.get(questionPossibleAnswerId);
            questionPossibleAnswer.incTotalUserAnswered();
        } catch (IndexOutOfBoundsException e) {
            throw new GameException("Illegal questionPossibleAnswerId: " + questionPossibleAnswerId, e);
        }
    }

    public boolean isCorrectAnswer(int userChosenAnswerId) {
        return correctAnswerIdOptional.map(correctAnswerId -> correctAnswerId == userChosenAnswerId).orElse(false);
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", questionText=" + questionText + ", questionPossibleAnswerList="
                + questionPossibleAnswerList + ", correctAnswerIdOptional=" + correctAnswerIdOptional
                + ", questionStatus=" + questionStatus + ", totalUserAnswered=" + totalUserAnswered + "]";
    }
}
