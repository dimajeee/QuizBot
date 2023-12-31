package org.example.Quiz;

import org.example.Quiz.QuizResponce;

public class QuizResponse implements QuizResponce {
    private int QuizCount = 3;
    private int CurrentQuizNumber = 0;
    private String[][] quizTable;
    private String Quiz;
    private String Answer;

    public int getQuizCount() {
        return QuizCount;
    }

    public String getQuiz() {
        return Quiz;
    }

    public String getAnswer() {
        return Answer;
    }


    public QuizResponse() {
        quizTable = new String[3][2];
        quizTable[0][0] = "Как называют жителей города Смоленска в РФ?";
        quizTable[0][1] = "Смоляне";
        quizTable[1][0] = "Чем их больше, тем масса меньше. Что это?";
        quizTable[1][1] = "Дырки";
        quizTable[2][0] = "Какая первая ближайшая планета к Солнцу?";
        quizTable[2][1] = "Меркурий";
    }

    public void UpdateQA() {
        Quiz = quizTable[CurrentQuizNumber][0];
        Answer = quizTable[CurrentQuizNumber][1];
        CurrentQuizNumber += 1;
    }

    public void ResetQuiz() {
        CurrentQuizNumber = 0;
    }
}
