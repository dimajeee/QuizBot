package org.example;


import org.example.DialogStatus.Start;

import java.util.ArrayList;

public class Handler {
    private final ArrayList<Person> persons;
    private final DataResponse dataResponse;
    public Handler() {
        persons = new ArrayList<Person>();
        dataResponse = new DataResponse();
    }
    private Response response = new Response();

    public Response handleWithoutResponse(String chatID, Request request) {
        Person person = getPersonByChatID(chatID);
        String HandleRequest = request.getRequest();
        String[] req = HandleRequest.split(" "); // добавить проверку на то, что все хорошо засплитилось
        if (person.getBotCondition()) {
            return handleWithResponse(person, req);
        }
        else {
            if (req[0].equals("/start"))
            {
                person.setBotCondition(true); //
                return handleWithResponse(person, req);

            }
            else if (req[0].equals("/stop")) {
                person.setBotCondition(false);
                return handleWithResponse(person, req);
            }
            else {
                response.setResponseFlag(false);
                return response;
            }
        }
    }

    public Response handleWithResponse (Person person, String[] req) {
        if (CheckCommand(req[0])) person.getDialogContext().setDialogStatus(new Start(person, dataResponse));
        response = person.getDialogContext().nextDialogContext(req);
        response.setResponseFlag(true);
        return response;
    }

    private Person getPersonByChatID(String chatID) {
        for (Person person : persons) {
            if (person.getChatID().equals(chatID)) {
                return person;
            }
        }
        persons.add(new Person(chatID));
        return persons.get(persons.size() - 1);
    }

    private boolean CheckCommand(String req) { // переделать
        return switch (req) {
            case "/start", "/stop", "/quiz", "/help" -> true;
            default -> false;
        };
    }


    }
