package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    public Handle handle = new Handle();
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
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        ResponseClass responseClass = handle.handleWithoutResponse(request);
        String Text = responseClass.getResponse();
        if (responseClass.responseFlag) {
            sendMessage.setText(Text);
            try {
                this.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
