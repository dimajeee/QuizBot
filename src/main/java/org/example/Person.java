package org.example;

import java.util.ArrayList;

public class Person {
    private ArrayList <Person> personBase;
    private String chatID;
    // private Response response;
    private boolean BotCondition = false;
    private DialogContext dialogContext;
    // private Request request;
    private GameQuiz gameQuiz;
    private QuizResponse quizResponse;
    public Person(String chatID) {
        this.chatID = chatID;
        gameQuiz = new GameQuiz();
        // response = new Response();
        dialogContext = new DialogContext();
        quizResponse = new QuizResponse();
        quizResponse.updateQuizBase();
        // request = new Request(req);
    }
    public Person (){
      personBase=new ArrayList<>();
      gameQuiz = new GameQuiz();
      dialogContext = new DialogContext();
      quizResponse = new QuizResponse();
      quizResponse.updateQuizBase();
    }
    public Person getPersonFromBase (int i){
        return personBase.get(i);
    }
    public Person getPersonByChatID(String chatID) {
        for (Person person : personBase) {
            if (person.getChatID().equals(chatID)) {
                return person;
            }
        }
        personBase.add(new Person(chatID));
        return personBase.get(personBase.size() - 1);
    }
    public DialogContext getDialogContext() {return dialogContext;}
    public void setBotCondition(boolean botCondition) {
        BotCondition = botCondition;
    }
    public GameQuiz getGameQuiz() {
        return gameQuiz;
    }
    public QuizResponse getQuizResponse() {
        return quizResponse;
    }
//    public Request getRequest() {
//        return request;
//    }
//    public Response getResponse() {return response;
//    }
    public String getChatID() {
        return chatID;
    }
    public boolean getBotCondition() {
        return BotCondition;
    }
}
