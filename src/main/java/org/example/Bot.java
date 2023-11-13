package org.example;

import org.example.Person.PersonClass;
import org.example.Response.ResponseClass;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
    ArrayList<PersonClass> persons = new ArrayList<PersonClass>();
    public Handle handle = new Handle();

    public ResponseClass responseClass = new ResponseClass();
    @Override
    public String getBotUsername() {
        return "QuizKD_bot";
    } // qqqkd_bot

    @Override
    public String getBotToken() {
        return "6000806341:AAFLcadyPTrN7qO4-CwYlY9LJl8lLOZ6Z0Q";
    } // 5915973194:AAFx3pZnoeBvfnzngDgYBAgqt86a-Hjsh3Y


    @Override
    public void onUpdateReceived(Update update) {
        String chatID = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText().toString();
        System.out.println(text);
        PersonClass person = getPersonByChatID(chatID, text);
//        RequestClass requestClass = new RequestClass(text);


//            handle.dataPack.requestClass.setRequest(text);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatID);
            handle.handleWithoutResponse(person);
            String Text = person.getResponseClass().getResponse();
            if (responseClass.responseFlag) {
                sendMessage.setText(Text);
                try {
                    this.execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        System.out.println(text);
    }

    private PersonClass getPersonByChatID(String chatID, String text) {
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getChatID().equals(chatID)) {
                persons.get(i).getRequestClass().setRequest(text);
                return persons.get(i);
            }
        }
        persons.add(new PersonClass(chatID, text));
        return persons.get(persons.size() - 1);
    }
}
