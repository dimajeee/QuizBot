package org.example;


public class Handle {
    public ResponseClass handle (RequestClass request){
        String request1=request.getRequest();
        String[] req=request1.split(" ");
        ResponseClass responseClass=new ResponseClass();
        DataResponse dataResponse= new DataResponse();
        switch (req[0]){
            case "/help":
                responseClass.setResponse(dataResponse.help);
                break;
            case "/start":
                responseClass.setResponse(dataResponse.start);
                break;
        }
        return responseClass;
    }
}
