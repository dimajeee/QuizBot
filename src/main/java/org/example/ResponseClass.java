package org.example;

public class ResponseClass implements Response{
    private String response;

    public ResponseClass(String response){
        this.response=response;
    }
    public ResponseClass(){}

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String getResponse() {
        return response;
    }
}
