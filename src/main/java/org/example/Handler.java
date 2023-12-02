package org.example;


import org.example.DialogStatus.Start;

import java.util.ArrayList;

public class Handler {
    Person personss=new Person();
    private final DataResponse dataResponse;
    public Handler() {

        dataResponse = new DataResponse();
    }
    private Response response = new Response();

    public Response handleWithoutResponse(String chatID, Request request) {
        Person person = personss.getPersonByChatID(chatID);
        System.out.println(person.getChatID());
        String HandleRequest = request.getRequest();
        String req = HandleRequest; // добавить проверку на то, что все хорошо засплитилось
        if (person.getBotCondition()) {
            return handleWithResponse(person, req);
        }
        else {
            if (req.equals("/start"))
            {
                person.setBotCondition(true); //
                return handleWithResponse(person, req);

            }
            else if (req.equals("/stop")) {
                person.setBotCondition(false);
                return handleWithResponse(person, req);
            }
            else {
                response.setResponseFlag(false);
                return response;
            }
        }
    }

    public Response handleWithResponse (Person person, String req) {
        if (CheckCommand(req)) person.getDialogContext().setDialogStatus(new Start(person, dataResponse));
        response = person.getDialogContext().nextDialogContext(req);
        response.setResponseFlag(true);
        return response;
    }



    private boolean CheckCommand(String req) { // переделать
        return switch (req) {
            case "/start", "/stop", "/quiz", "/help" -> true;
            default -> false;
        };
    }


    }
