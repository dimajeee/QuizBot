package org.example.Person;

import org.example.DialogContext;
import org.example.GameQuiz.GameQuizClass;
import org.example.GameQuizResponse.QuizResponce;
import org.example.GameQuizResponse.QuizResponse;
import org.example.Request.RequestClass;
import org.example.Response.ResponseClass;

public class PersonClass {
    private String chatID;
    private ResponseClass responseClass;
    private boolean BotCondition = false;
    public DialogContext dialogContext;
    private RequestClass requestClass;
    private GameQuizClass gameQuizClass;
    private QuizResponce quizResponce;
    public PersonClass(String chatID, String req) {
        this.chatID = chatID;
        gameQuizClass = new GameQuizClass();
        responseClass = new ResponseClass();
        dialogContext = new DialogContext();
        quizResponce = new QuizResponse();
        requestClass = new RequestClass(req);
    }
    public void setBotCondition(boolean botCondition) {
        BotCondition = botCondition;
    }
    public GameQuizClass getGameQuizClass() {
        return gameQuizClass;
    }
    public QuizResponce getQuizResponce() {
        return quizResponce;
    }
    public RequestClass getRequestClass() {
        return requestClass;
    }
    public ResponseClass getResponseClass() {
        return responseClass;
    }
    public String getChatID() {
        return chatID;
    }
    public boolean getBotCondition() {
        return BotCondition;
    }
}
