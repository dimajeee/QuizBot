package org.example.Request;

public class RequestClass implements Request {

    public String req;

    public RequestClass(String req){this.req = req;}

    @Override
    public String getRequest() {
        return req;
    }
}
