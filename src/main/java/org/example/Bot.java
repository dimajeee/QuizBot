package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    private final Handler handle;
    public Bot() {
        handle = new Handler();
    }
    private final Config config = new Config();
    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {return config.getToken();}


    @Override
    public void onUpdateReceived(Update update) {
        String chatID = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText(); // добавить проверку на null, try catch
        Request request = new Request(text);
        System.out.println("пришло сообщение: "+text);
        ReplyKeyboardMarkup keyboardMarkup=new ReplyKeyboardMarkup();


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        Response response = handle.handleWithoutResponse(chatID, request);
        if (response.isResponseFlag()) {
            if (!response.getKeyboardRows().isEmpty()) {
                sendMessage.setText(response.getResponse());
                keyboardMarkup.setKeyboard(response.getKeyboardRows());
                sendMessage.setReplyMarkup(keyboardMarkup);
            }
            else {
                sendMessage.setText(response.getResponse());
                ReplyKeyboardRemove replyKeyboardRemove=new ReplyKeyboardRemove(true);
                sendMessage.setReplyMarkup(replyKeyboardRemove);
            }
            try {
                this.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

        //System.out.println(text);
    }


}
