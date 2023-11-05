package org.example;

public class Handle {
    public Response handle (Request request){
        String request1=request.getRequest();
        String[] req=request1.split(" ");
        ResponseClass r;
        switch (req[0]){
            case "/help":
                 r.setResponse();
        }
        return r;
    }
}
