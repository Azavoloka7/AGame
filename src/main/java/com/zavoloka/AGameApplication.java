package com.zavoloka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(AGameApplication.class, args);
	}
}
/*Цей клас, AGameApplication, є основним класом Spring Boot додатка і виконує ключову роль у запуску додатка. Ось його роль та функції в проекті:

Анотація @SpringBootApplication:

Ця анотація вказує, що цей клас є головним класом для Spring Boot додатка, а також включає в себе деякі стандартні налаштування, які забезпечують правильну роботу додатка. Вона об'єднує три анотації: @Configuration, @EnableAutoConfiguration і @ComponentScan.
Метод main:

Метод main є входом у додаток. Він має статичний модифікатор, тобто він може бути викликаний без створення екземпляра класу. В цьому методі викликається статичний метод run класу SpringApplication. Цей метод запускає Spring Boot додаток.
Запуск Spring Boot Додатка:

Виклик SpringApplication.run(AGameApplication.class, args) ініціює процес запуску Spring Boot додатка. Spring Boot автоматично визначає конфігурацію, сканує пакети для компонентів і виконує необхідні автоконфігурації.
Автоматична Конфігурація:

Завдяки анотації @SpringBootApplication, Spring Boot автоматично здійснює автоконфігурацію, визначаючи налаштування додатка на основі наявних бібліотек та конвенцій.
Запуск Вбудованого Tomcat:

Зазвичай, Spring Boot додаток має вбудований контейнер Tomcat, який автоматично запускається при виклику SpringApplication.run. Додаток буде доступний за адресою http://localhost:8080.
Керування Контекстом Додатка:

Клас AGameApplication відповідає за створення і керування контекстом додатка, включаючи усі біни та складові, необхідні для коректної роботи додатка.
Загальною метою цього класу є ініціалізація та запуск Spring Boot додатка, а також забезпечення необхідного конфігурування для його коректної роботи.*/