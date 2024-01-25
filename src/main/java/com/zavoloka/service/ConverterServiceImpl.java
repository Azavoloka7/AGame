package com.zavoloka.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.zavoloka.dto.GameDto;
import com.zavoloka.dto.QuestionDto;
import com.zavoloka.dto.QuestionPossibleAnswerDto;
import com.zavoloka.exception.GameException;
import com.zavoloka.model.Game;
import com.zavoloka.model.Question;
import com.zavoloka.model.QuestionPossibleAnswer;
import com.zavoloka.resolver.Resolver;

@Service
public class ConverterServiceImpl implements ConverterService {

	@Override
	public Game toModel(GameDto gameDto, Resolver resolver) {

		Integer pointsForCorrectAnswer = gameDto.getPointsForCorrectAnswer();

		if (pointsForCorrectAnswer == null) {
			throw new GameException("field pointsForCorrectAnswer can't be null");
		}

		List<QuestionDto> questions = gameDto.getQuestions();

		if (questions == null || questions.isEmpty()) {
			throw new GameException("field questions can't be null or an empty array");
		}

		List<Question> questionList = IntStream.range(0, questions.size())
				.mapToObj(i -> toModel(i, questions.get(i)))
				.collect(Collectors.toList());

		return new Game(pointsForCorrectAnswer, questionList, resolver);
	}

	private Question toModel(int id, QuestionDto questionDto) {

		String questionText = questionDto.getQuestionText();

		if (questionText == null) {
			throw new GameException("field questionText can't be null");
		}

		List<QuestionPossibleAnswerDto> questionPossibleAnswers = questionDto.getQuestionPossibleAnswers();

		if (questionPossibleAnswers == null || questionPossibleAnswers.isEmpty()) {
			throw new GameException("field questionPossibleAnswers can't be null or an empty array");
		}

		List<QuestionPossibleAnswer> questionPossibleAnswerList = IntStream.range(0, questionPossibleAnswers.size())
				.mapToObj(i -> toModel(i, questionPossibleAnswers.get(i)))
				.collect(Collectors.toList());

		return new Question(id, questionText, questionPossibleAnswerList);
	}

	private QuestionPossibleAnswer toModel(int id, QuestionPossibleAnswerDto questionPossibleAnswerDto) {

		return new QuestionPossibleAnswer(id, questionPossibleAnswerDto.getPossibleAnswer());
	}

	@Override
	public QuestionDto toDto(Question question) {

		List<QuestionPossibleAnswer> questionPossibleAnswerList = question.getQuestionPossibleAnswerList();

		List<QuestionPossibleAnswerDto> questionPossibleAnswers = IntStream.range(0, questionPossibleAnswerList.size())
				.mapToObj(i -> toModel(questionPossibleAnswerList.get(i)))
				.collect(Collectors.toList());

		return new QuestionDto(question.getId(), question.getQuestionText(), questionPossibleAnswers);
	}

	private QuestionPossibleAnswerDto toModel(QuestionPossibleAnswer questionPossibleAnswer) {

		return new QuestionPossibleAnswerDto(questionPossibleAnswer.getId(), questionPossibleAnswer.getPossibleAnswer());
	}
}
