package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "QuizKD_bot";
    }

    @Override
    public String getBotToken() {
        return "6000806341:AAFLcadyPTrN7qO4-CwYlY9LJl8lLOZ6Z0Q";
    }

    @Override
    public void onUpdateReceived(Update update) {
        String chatID = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText().toString();


        RequestClass request = new RequestClass(text);
        Handle handle = new Handle();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setText(handle.handle(request).getResponse());

        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}
