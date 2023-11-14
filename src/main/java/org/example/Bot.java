package org.example;

import org.example.Person.PersonClass;
import org.example.Response.ResponseClass;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
    public Bot() {
        persons = new ArrayList<PersonClass>();
        handle = new Handle();
    }
    ArrayList<PersonClass> persons;
    public Handle handle;

    @Override
    public String getBotUsername() {
        return "qqqkd_bot";
    } //

    @Override
    public String getBotToken() {
        return "5915973194:AAFx3pZnoeBvfnzngDgYBAgqt86a-Hjsh3Y";
    } // 5915973194:AAFx3pZnoeBvfnzngDgYBAgqt86a-Hjsh3Y


    @Override
    public void onUpdateReceived(Update update) {
        String chatID = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText().toString();
        System.out.println(text);
        PersonClass person = getPersonByChatID(chatID, text);


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        handle.handleWithoutResponse(person);
        String Text = person.getResponseClass().getResponse();
        if (person.getResponseClass().responseFlag) {
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
