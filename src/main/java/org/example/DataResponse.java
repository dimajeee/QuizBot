package org.example;

public class DataResponse {
    public String start = "Привет!Я бот с викторинами. Если тебе скучно, я тебя развлеку. Напиши /quiz и мы начнем";
    public String help = "/quiz начало викторины\n" +
            "/stopquiz конец викторины\n" +
            "/score счет игры" +
            "/rereply переответ на последний вопрос\n" +
            "/stopbot остановка бота";
    public String quiz = "Это викторина, давай играть!";
    public String falseAnswer = "Вы ответили неправильно!";
    public String trueAnswer = "Ваш ответ верен!";
    public String stopQuiz = "Викторина остановлена, можешь вернуться в любое другое время!";
    public String notRemainQuiz = "Вопросов больше не осталось, приходи позже!";
    public String nonsense = "Ты ввел неправильную команду\nВведи /help, чтобы узнать список команд";
}
