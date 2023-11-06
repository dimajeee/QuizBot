package org.example;


public class Handle {
    public ResponseClass handle (RequestClass request){
        String request1 = request.getRequest();
        String[] req = request1.split(" ");
        ResponseClass responseClass = new ResponseClass();
        DataResponse dataResponse = new DataResponse();
        DialogManagerClass dialogManager = new DialogManagerClass();
        switch (req[0]){
            case "/help":
                responseClass.setResponse(dataResponse.help);
                break;
            case "/start":
                responseClass.setResponse(dataResponse.start);
                break;
            case "/quiz":
                dialogManager.setQuizGame(true);
                if (dialogManager.CheckNumberRemainQuiz() == true) {
                    dialogManager.setWaitAnswer("5");
                }
                else {
                    responseClass.setResponse("");
                }
                break;
            case "/stopquiz":
                dialogManager.setQuizGame(false);
                responseClass.setResponse("");
                break;
            default:
                if (dialogManager.CheckQuizGame() == true) {
                    if (dialogManager.CheckAnswer(req[0])){
                        responseClass.setResponse("");
                    }
                    else {
                        responseClass.setResponse("");
                    }

                }
                else {
                    responseClass.setResponse("");
                }

                break;
        }
        return responseClass;
    }
}
