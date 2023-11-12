package org.example;

import org.example.GameQuiz.GameQuizClass;
import org.example.GameQuizResponse.QuizResponce;
import org.example.GameQuizResponse.QuizResponse;
import org.example.Request.RequestClass;
import org.example.Response.DataResponse;
import org.example.Response.ResponseClass;

public class DataPack {
    public ResponseClass responseClass;
    public DataResponse dataResponse;
    public QuizResponce quizResponse;
    public GameQuizClass gameQuizClass;
    public RequestClass requestClass = new RequestClass("");

    public DataPack() {
        responseClass = new ResponseClass();
        dataResponse = new DataResponse();
        quizResponse = new QuizResponse();
        gameQuizClass = new GameQuizClass();
    }
}
