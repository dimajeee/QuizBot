package org.example;


public class Handle {
    public ResponseClass responseClass = new ResponseClass();
    public DataResponse dataResponse = new DataResponse();
    public DialogManagerClass dialogManager = new DialogManagerClass();
    public QuizResponse quizResponse = new QuizResponse();
    public ResponseClass handle (RequestClass request){
        String request1 = request.getRequest();
        String[] req = request1.split(" ");
        switch (req[0]){
            case "/help":
                responseClass.setResponse(dataResponse.help);
                break;
            case "/start":
                responseClass.setResponse(dataResponse.start);
                break;
            case "/quiz":
                dialogManager.setQuizGame(true);
                quizResponse.UpdateQA();
                if (dialogManager.CheckNumberRemainQuiz(quizResponse.QuizCount) == true) {
                    dialogManager.setWaitAnswer(quizResponse.Answer);
                    responseClass.setResponse(dataResponse.quiz + "\n" +  quizResponse.Quiz);
                }
                else {
                    responseClass.setResponse(dataResponse.notRemainQuiz);
                }
                break;
            case "/stopquiz":
                dialogManager.setQuizGame(false);
                responseClass.setResponse(dataResponse.stopQuiz);
                break;
            case "/score":
                break;
            case "/restart":
                break;

            default:
                if (dialogManager.CheckQuizGame() == true) {
                    String response1;
                    if (dialogManager.CheckAnswer(req[0])){
                        response1 = dataResponse.trueAnswer;
                    }
                    else {
                        response1 = dataResponse.falseAnswer;
                    }
                    String response2;
                    quizResponse.UpdateQA();
                    if (dialogManager.CheckNumberRemainQuiz(quizResponse.QuizCount) == true) {
                        dialogManager.setWaitAnswer(quizResponse.Quiz);
                        response2 = quizResponse.Quiz;
                    }
                    else {
                        response2 = dataResponse.notRemainQuiz;
                    }
                    responseClass.setResponse(response1 + "\n" + response2);

                }
                else {
                    responseClass.setResponse("");

                }

                break;
        }
        return responseClass;
    }
}
