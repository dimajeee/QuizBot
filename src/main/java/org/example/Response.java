package org.example;

public class Response {
    private String response;
    private boolean responseFlag;
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
