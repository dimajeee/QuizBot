package org.example;

public class Person {
    private final String chatID;
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
        // request = new Request(req);
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
