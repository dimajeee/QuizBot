package org.example;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class Response {
    private String response;
    private boolean responseFlag;
    private ArrayList<KeyboardRow> keyboardRows=new ArrayList<>();

    public ArrayList<KeyboardRow> getKeyboardRows() {
        return keyboardRows;
    }

    public void setKeyboardRows(ArrayList<KeyboardRow> keyboardRows) {
        this.keyboardRows = keyboardRows;
    }

    public Response(String response){
        this.response=response;
    }
    public Response(){}
    public void setResponse(String response) {
        this.response = response;
    }
    public String getResponse() {
        return response;
    }
    public boolean isResponseFlag() {
        return responseFlag;
    }
    public void setResponseFlag(boolean responseFlag) {
        this.responseFlag = responseFlag;
    }
}
