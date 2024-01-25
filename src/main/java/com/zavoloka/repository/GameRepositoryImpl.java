package com.zavoloka.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.zavoloka.exception.GameException;
import com.zavoloka.model.Game;

@Repository
public class GameRepositoryImpl implements GameRepository {

	private final Map<Integer, Game> gameIdToGameMap;
	private final AtomicInteger nextGameId;

	public GameRepositoryImpl() {

		gameIdToGameMap = new ConcurrentHashMap<>();
		nextGameId = new AtomicInteger(0);
	}

	@Override
	public int save(Game game) {

		int gameId = getNextGameId();

		gameIdToGameMap.put(gameId, game);

		return gameId;
	}

	@Override
	public Game getById(int id) {

		Game game = gameIdToGameMap.get(id);

		if (game == null) {
			throw new GameException("failed to find Game with id: " + id);
		}

		return game;
	}

	@Override
	public void deleteAll() {

		gameIdToGameMap.clear();
		nextGameId.set(0);
	}

	private int getNextGameId() {

		return nextGameId.incrementAndGet();
	}
}
/*Цей клас, GameRepositoryImpl, є реалізацією інтерфейсу GameRepository. Він представляє собою репозиторій для
 зберігання і взаємодії з об'єктами Game у вашому проекті. Основна його роль - це управління інформацією про гри в
  пам'яті, зокрема додавання, видалення та отримання гри за її ідентифікатором.

Давайте розглянемо ключові елементи та їхні функції:

Поля класу:

gameIdToGameMap: Це ConcurrentHashMap, яка використовується для зберігання об'єктів Game. Ключем є ідентифікатор гри, а
значенням - сам об'єкт Game.
nextGameId: Атомарний лічильник, який використовується для генерації унікальних ідентифікаторів гри.
Конструктор:

Створює об'єкт GameRepositoryImpl і ініціалізує ConcurrentHashMap та атомарний лічильник.
Метод save(Game game):

Зберігає переданий об'єкт Game у репозиторії, присвоюючи йому унікальний ідентифікатор (за допомогою getNextGameId()).
Метод getById(int id):

Повертає об'єкт Game за його ідентифікатором. Якщо гра не знайдена, генерує виняток GameException.
Метод deleteAll():

Очищає всі записи в репозиторії, включаючи об'єкти Game, і скидає атомарний лічильник до початкового значення.
Приватний метод getNextGameId():

Цей метод використовує атомарний лічильник для генерації нового унікального ідентифікатора гри при додаванні нової гри
в репозиторій.
Загалом, цей клас є важливою складовою вашого проекту, оскільки він відповідає за управління грою та забезпечення доступу
до неї через взаємодію з базою даних у пам'яті.*/