package org.example;

import java.util.ArrayList;

public class Person {
    private static final ArrayList <Person> personBase = new ArrayList<Person>();
    private String chatID;
    private boolean BotCondition = false;
    private final DialogContext dialogContext;
    private final GameQuiz gameQuiz;
    private final QuizResponse quizResponse;
    public Person (){
      gameQuiz = new GameQuiz();
      dialogContext = new DialogContext();
      quizResponse = new QuizResponse();
      quizResponse.updateQuizBase();
    }
    public Person(String chatID){
        this(); // вызов предыдущего конструктора
        this.chatID = chatID;
    }
//    public Person getPersonFromBase (int i){
//        return personBase.get(i);
//    }
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
    public boolean getBotCondition() {
        return BotCondition;
    }
    public GameQuiz getGameQuiz() {return gameQuiz;}
    public QuizResponse getQuizResponse() {
        return quizResponse;
    }
    public String getChatID() {
        return chatID;
    }

}
