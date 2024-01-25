// app.js

var questionsData = [
    {
        question: "What is the capital of France?",
        answers: ["Berlin", "Paris", "Madrid", "London"],
        correctAnswer: 1
    },
    // Add more questions as needed
];

var currentQuestionIndex = 0;
var score = 0;

function startGame() {
    alert("Game started!");
    displayNextQuestion();
}

function displayNextQuestion() {
    var gameBoard = document.getElementById("game-board");

    if (currentQuestionIndex < questionsData.length) {
        var currentQuestion = questionsData[currentQuestionIndex];

        // Display question
        var questionElement = createQuestionElement(currentQuestionIndex + 1, currentQuestion.question);
        gameBoard.innerHTML = "";
        gameBoard.appendChild(questionElement);

        // Display answer options
        currentQuestion.answers.forEach(function (answer, answerIndex) {
            var answerElement = createAnswerElement(answerIndex + 1, answer);
            gameBoard.appendChild(answerElement);

            // Add click event listener to each answer
            answerElement.addEventListener("click", function () {
                handleAnswerClick(answerIndex + 1, currentQuestion.correctAnswer);
            });
        });

        currentQuestionIndex++;
    } else {
        alert("Game Over! Your final score is: " + score);
    }
}

function createQuestionElement(questionNumber, questionText) {
    var questionElement = document.createElement("p");
    questionElement.textContent = "Question " + questionNumber + ": " + questionText;
    return questionElement;
}

function createAnswerElement(answerNumber, answerText) {
    var answerElement = document.createElement("p");
    answerElement.textContent = "Option " + answerNumber + ": " + answerText;
    return answerElement;
}

function handleAnswerClick(selectedAnswer, correctAnswer) {
    if (selectedAnswer === correctAnswer) {
        alert("Correct!");
        score += 10;
    } else {
        alert("Incorrect. The correct answer is Option " + correctAnswer);
    }

    // Display next question
    displayNextQuestion();
}

// Update score function is not needed in this version
