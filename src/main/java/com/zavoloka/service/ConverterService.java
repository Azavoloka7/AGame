package com.zavoloka.service;

import com.zavoloka.dto.GameDto;
import com.zavoloka.dto.QuestionDto;
import com.zavoloka.model.Game;
import com.zavoloka.model.Question;
import com.zavoloka.resolver.Resolver;

public interface ConverterService {

	Game toModel(GameDto gameDto, Resolver resolver);
	QuestionDto toDto(Question question);
}
