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
            case "/start":
                Start();
                break;
            case "/help":
                Help();
                break;
            case "/quiz":
                Quiz();
                break;
            case "/stopquiz":
                StopQuiz();
                break;
            case "/score":
                Score();
                break;
            case "/restartquiz":
                Restart();
                break;
            case "/rereply":
                Rereply();
                break;
            case "/stop":
                Stop();
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
                    if (dialogManager.CheckNumberRemainQuiz(quizResponse.getQuizCount()) == true) {
                        quizResponse.UpdateQA();
                        dialogManager.setWaitAnswer(quizResponse.getAnswer());
                        response2 = quizResponse.getQuiz();
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




    private void Quiz() {
        dialogManager.setQuizGame(true);
        dialogManager.ResetScore();
        quizResponse.ResetQuiz();
        quizResponse.UpdateQA();
        if (dialogManager.CheckNumberRemainQuiz(quizResponse.getQuizCount()) == true) {
            dialogManager.setWaitAnswer(quizResponse.getAnswer());
            responseClass.setResponse(dataResponse.quiz + "\n" + quizResponse.getQuiz());
        }
        else {
            responseClass.setResponse(dataResponse.notRemainQuiz);
        }
    }



    private void Help() {
        responseClass.setResponse(dataResponse.help);
    }

    private void Start() {
        responseClass.setResponse(dataResponse.start);
    }

    private void StopQuiz() {
        dialogManager.setQuizGame(false);
        responseClass.setResponse(dataResponse.stopQuiz);
        dialogManager.ResetScore();
    }

    private void Score() {
        String score = dialogManager.GetScore();
        String[] splitScore = score.split(" ");
        responseClass.setResponse(dataResponse.TrueAnswerScore + splitScore[0] + "\n" +
                dataResponse.FalseAnswerScore + splitScore[1] + "\n" +
                dataResponse.AnswerScore + splitScore[2] + "\n");
    }

    private void Restart() {
        Quiz();
    }

    private void Rereply() {
    }

    private void Stop() {
    }
}
