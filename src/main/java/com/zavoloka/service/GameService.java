package com.zavoloka.service;

import com.zavoloka.dto.AnswerQuestionRequestDto;
import com.zavoloka.dto.AnswerQuestionResponseDto;
import com.zavoloka.dto.GameDto;
import com.zavoloka.dto.LeaderboardResponseDto;
import com.zavoloka.dto.QuestionDto;

public interface GameService {

	int save(GameDto gameDto);
	QuestionDto getQuestionByUsername(int gameId, String username);
	AnswerQuestionResponseDto userAnswerQuestion(int gameId, String username, AnswerQuestionRequestDto answerQuestionRequestDto);
	LeaderboardResponseDto getLeaderboard(int gameId);
}
