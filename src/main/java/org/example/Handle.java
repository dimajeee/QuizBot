package org.example;


public class Handle {
    public Response handle (Request request){
        String request1=request.getRequest();
        String[] req=request1.split(" ");
        ResponseClass responseClass=new ResponseClass();
        DataResponse dataResponse= new DataResponse();
        switch (req[0]){
            case "/help":
                 responseClass.setResponse(dataResponse.help);
                 break;
        }
        return responseClass;
    }
}
