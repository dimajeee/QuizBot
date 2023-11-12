package org.example.Request;

public class RequestClass implements Request {

    private String request;

    public RequestClass(String request){
        this.request = request;
    }
    public void setRequest(String request) {
        this.request = request;
    }
    @Override
    public String getRequest() {
        return request;
    }
}
