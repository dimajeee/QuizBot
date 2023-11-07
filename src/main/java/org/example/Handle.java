package org.example;


public class Handle {
    public ResponseClass responseClass = new ResponseClass();
    public DataResponse dataResponse = new DataResponse();
    public DialogManagerClass dialogManager = new DialogManagerClass();
    public QuizResponse quizResponse = new QuizResponse();
    public ResponseClass handle (RequestClass request){
        String HandleRequest = request.getRequest();
        String[] req = HandleRequest.split(" ");
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
                    responseClass.setResponse(dataResponse.quiz + "\n" + quizResponse.Quiz);
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
                    System.out.println(quizResponse.CurrentQuizNumber);
                    if (dialogManager.CheckNumberRemainQuiz(quizResponse.QuizCount) == true) {
                        quizResponse.UpdateQA();
                        dialogManager.setWaitAnswer(quizResponse.Answer);
                        response2 = quizResponse.Quiz;
                    }
                    else {
                        response2 = dataResponse.notRemainQuiz;
                    }

                    responseClass.setResponse(response1 + "\n" + response2);

                }
                else {
                    responseClass.setResponse(dataResponse.nonsense);

                }

                break;
        }
        return responseClass;
    }
}
