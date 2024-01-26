package com.zavoloka.provider;

import com.zavoloka.model.Question;
import com.zavoloka.model.QuestionPossibleAnswer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class QuestionProvider {

    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

// Question 1
        Question question1 = new Question(1, "What is the capital of France?", Arrays.asList(
                new QuestionPossibleAnswer(1, "Berlin"),
                new QuestionPossibleAnswer(2, "Paris"),
                new QuestionPossibleAnswer(3, "Madrid"),
                new QuestionPossibleAnswer(4, "London")
        ));
        question1.setCorrectAnswerIdOptional(Optional.of(2));
        questions.add(question1);

// Question 2
        Question question2 = new Question(2, "In which year did World War I begin?", Arrays.asList(
                new QuestionPossibleAnswer(1, "1910"),
                new QuestionPossibleAnswer(2, "1914"),
                new QuestionPossibleAnswer(3, "1918"),
                new QuestionPossibleAnswer(4, "1922")
        ));
        question2.setCorrectAnswerIdOptional(Optional.of(2));
        questions.add(question2);

// Question 3
        Question question3 = new Question(3, "Quelle est la capitale du Canada?", Arrays.asList(
                new QuestionPossibleAnswer(1, "Ottawa"),
                new QuestionPossibleAnswer(2, "Toronto"),
                new QuestionPossibleAnswer(3, "Montreal"),
                new QuestionPossibleAnswer(4, "Vancouver")
        ));
        question3.setCorrectAnswerIdOptional(Optional.of(1));
        questions.add(question3);

// Question 4
        Question question4 = new Question(4, "What is the largest mammal in the world?", Arrays.asList(
                new QuestionPossibleAnswer(1, "Elephant"),
                new QuestionPossibleAnswer(2, "Blue Whale"),
                new QuestionPossibleAnswer(3, "Giraffe"),
                new QuestionPossibleAnswer(4, "Hippopotamus")
        ));
        question4.setCorrectAnswerIdOptional(Optional.of(2));
        questions.add(question4);

// Question 5
        Question question5 = new Question(5, "Cuál es la moneda oficial de México?", Arrays.asList(
                new QuestionPossibleAnswer(1, "Peso"),
                new QuestionPossibleAnswer(2, "Dollar"),
                new QuestionPossibleAnswer(3, "Euro"),
                new QuestionPossibleAnswer(4, "Yen")
        ));
        question5.setCorrectAnswerIdOptional(Optional.of(1));
        questions.add(question5);

// Question 6
        Question question6 = new Question(6, "What is the capital of Australia?", Arrays.asList(
                new QuestionPossibleAnswer(1, "Sydney"),
                new QuestionPossibleAnswer(2, "Melbourne"),
                new QuestionPossibleAnswer(3, "Canberra"),
                new QuestionPossibleAnswer(4, "Brisbane")
        ));
        question6.setCorrectAnswerIdOptional(Optional.of(3));
        questions.add(question6);

// Question 7
        Question question7 = new Question(7, "Wie heißt die Hauptstadt von Deutschland?", Arrays.asList(
                new QuestionPossibleAnswer(1, "Vienna"),
                new QuestionPossibleAnswer(2, "Berlin"),
                new QuestionPossibleAnswer(3, "Madrid"),
                new QuestionPossibleAnswer(4, "Paris")
        ));
        question7.setCorrectAnswerIdOptional(Optional.of(2));
        questions.add(question7);

// Question 8
        Question question8 = new Question(8, "Which planet is known as the Red Planet?", Arrays.asList(
                new QuestionPossibleAnswer(1, "Venus"),
                new QuestionPossibleAnswer(2, "Mars"),
                new QuestionPossibleAnswer(3, "Jupiter"),
                new QuestionPossibleAnswer(4, "Saturn")
        ));
        question8.setCorrectAnswerIdOptional(Optional.of(2));
        questions.add(question8);

// Question 9
        Question question9 = new Question(9, "Cuál es la lengua oficial de Brasil?", Arrays.asList(
                new QuestionPossibleAnswer(1, "Portuguese"),
                new QuestionPossibleAnswer(2, "Spanish"),
                new QuestionPossibleAnswer(3, "Italian"),
                new QuestionPossibleAnswer(4, "French")
        ));
        question9.setCorrectAnswerIdOptional(Optional.of(1));
        questions.add(question9);

// Question 10
        Question question10 = new Question(10, "What is the capital of South Korea?", Arrays.asList(
                new QuestionPossibleAnswer(1, "Seoul"),
                new QuestionPossibleAnswer(2, "Tokyo"),
                new QuestionPossibleAnswer(3, "Beijing"),
                new QuestionPossibleAnswer(4, "Manila")
        ));
        question10.setCorrectAnswerIdOptional(Optional.of(1));
        questions.add(question10);

// Question 11
        Question question11 = new Question(11, "In which year did the Titanic sink?", Arrays.asList(
                new QuestionPossibleAnswer(1, "1905"),
                new QuestionPossibleAnswer(2, "1912"),
                new QuestionPossibleAnswer(3, "1920"),
                new QuestionPossibleAnswer(4, "1931")
        ));
        question11.setCorrectAnswerIdOptional(Optional.of(2));
        questions.add(question11);

// Question 12
        Question question12 = new Question(12, "Quelle est la couleur du cheval blanc de Napoléon?", Arrays.asList(
                new QuestionPossibleAnswer(1, "Black"),
                new QuestionPossibleAnswer(2, "White"),
                new QuestionPossibleAnswer(3, "Brown"),
                new QuestionPossibleAnswer(4, "Gray")
        ));
        question12.setCorrectAnswerIdOptional(Optional.of(2));
        questions.add(question12);

// Question 13
        Question question13 = new Question(13, "What is the currency of Japan?", Arrays.asList(
                new QuestionPossibleAnswer(1, "Yuan"),
                new QuestionPossibleAnswer(2, "Dollar"),
                new QuestionPossibleAnswer(3, "Yen"),
                new QuestionPossibleAnswer(4, "Euro")
        ));
        question13.setCorrectAnswerIdOptional(Optional.of(3));
        questions.add(question13);

        // Додати інші питання за аналогією...

        return questions;
    }
}
/*Клас QuestionProvider відіграє важливу роль у вашому проекті та виконує наступні функції:

Централізоване Зберігання Питань:

Цей клас діє як централізоване сховище для зберігання списку заздалегідь визначених питань, які можуть використовуватися
у вашій грі.
Легке Обслуговування:

За допомогою цього класу стає легше управляти та обслуговувати питання. Якщо вам коли-небудь знадобиться додати, змінити
чи видалити питання, ви можете зробити це в одному місці.
Організація Коду:

Клас сприяє організації вашого коду. Він інкапсулює логіку, пов'язану з генерацією питань, що полегшує розуміння та
роботу розробників над кодовою базою.
Повторне Використання:

Питання, які надає цей клас, можна використовувати в усьому вашому проекті. Інші частини вашого додатку, такі як логіка
гри чи інтерфейс користувача, можуть отримати доступ до цього класу для отримання питань.
Масштабованість:

При зростанні вашого проекту і потребі в нових питаннях цей клас забезпечує можливість легко додавати нові елементи без
<<<<<<< HEAD
внесення змін в інші частини системи.*/

