package com.zavoloka.resolver;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.zavoloka.model.Question;
import com.zavoloka.model.QuestionPossibleAnswer;
import com.zavoloka.model.QuestionStatus;

@Component
public class MajorityVoteResolverImpl implements Resolver {

	private static final int MIN_USERS_ANSWERED = 6;
	private static final int MAX_USERS_ANSWERED = 11;
	private static final double MIN_PERCENTAGE_USER_VOTE_FOR_CORRECT_ANSWER = 75.0;
	
	@Override
	public void resolveAndSetStatus(Question question) {

		if (question.getTotalUserAnswered() < MIN_USERS_ANSWERED) {
			
			return;
			
		} else if (question.getTotalUserAnswered() > MAX_USERS_ANSWERED) {
			
			question.setQuestionStatus(QuestionStatus.UNRESOLVED);
			
		} else {
			
			List<QuestionPossibleAnswer> questionPossibleAnswerList = question.getQuestionPossibleAnswerList();
			
			OptionalInt correctAnswerIdOptionalInt = IntStream.range(0, questionPossibleAnswerList.size())
				// If an answer got more than 75% of the users, it will be determined as the correct answer
				.filter(
					i -> questionPossibleAnswerList.get(i).getTotalUserAnswered() * (double)100 / question.getTotalUserAnswered() >= MIN_PERCENTAGE_USER_VOTE_FOR_CORRECT_ANSWER
				).findFirst();
			
			if (correctAnswerIdOptionalInt.isPresent()) {

				Optional<Integer> correctAnswerIdOptional = Optional.of(correctAnswerIdOptionalInt.getAsInt());
				
				question.setQuestionStatus(QuestionStatus.RESOLVED);
				question.setCorrectAnswerIdOptional(correctAnswerIdOptional);
			}
		}
	}
}
/*Цей клас, MajorityVoteResolverImpl, є реалізацією інтерфейсу Resolver у вашому проекті. Його основною метою є визначення статусу Question на основі механізму більшості голосів.

Основні аспекти того, що робить цей клас у вашому проекті:

Реалізація Інтерфейсу:

Цей клас реалізує інтерфейс Resolver, що свідчить про те, що він надає конкретну реалізацію для вирішення статусу Question.
Логіка Більшості Голосів:

Метод resolveAndSetStatus - ключовий метод у цьому класі. Він приймає Question в якості параметра та аналізує дані, що стосуються відповідей користувачів, щоб визначити статус питання.
Якщо загальна кількість користувачів, які відповіли на питання, менше визначеного порогу (MIN_USERS_ANSWERED), метод повертається без внесення змін. Це означає, що питання ще не готове для вирішення.
Якщо загальна кількість користувачів, які відповіли на питання, перевищує інший поріг (MAX_USERS_ANSWERED), питання встановлюється у стан "невирішене". Це може означати,
що питання не отримало достатньо голосів для визначення правильної відповіді.
Якщо загальна кількість користувачів знаходиться в припустимому діапазоні, метод виконує логіку більшості голосів для визначення правильної відповіді. Він перевіряє,
чи отримала яка-небудь з відповідей більше визначеного відсотка голосів (MIN_PERCENTAGE_USER_VOTE_FOR_CORRECT_ANSWER).
Якщо так, воно встановлює статус питання як "вирішене" та вказує правильну відповідь.
Використання OptionalInt:

Використання OptionalInt є помітним у цьому контексті. Він використовується для представлення необов'язкового цілочисельного значення,
і в даному випадку він представляє індекс правильної відповіді в списку можливих відповідей.
Анотація Component:

Анотація @Component вказує на те, що цей клас є компонентом Spring і підходить для сканування компонентів. Це дозволяє Spring автоматично виявляти і створювати цей клас як бін.
Узагальнюючи, цей клас відповідає за визначення статусу Question на основі механізму більшості голосів, враховуючи кількість користувачів, які відповіли на питання, та їхні вибори.
Якщо існує чітка більшість, він встановлює статус питання як "вирішене" та вказує правильну відповідь.*/