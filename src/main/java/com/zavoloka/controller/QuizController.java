package com.zavoloka.controller;

import com.zavoloka.model.Question;
import com.zavoloka.provider.QuestionProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class QuizController {

    @GetMapping("/quiz")
    public String showQuiz(Model model) {
        List<Question> questions = QuestionProvider.getQuestions();
        model.addAttribute("questions", questions);
        return "quiz";
    }
}
