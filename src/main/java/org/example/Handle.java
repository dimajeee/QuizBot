package org.example;


public class Handle {
    private boolean botCondition = false;
    public ResponseClass responseClass = new ResponseClass();
    public DataResponse dataResponse = new DataResponse();
    public DialogManagerClass dialogManager = new DialogManagerClass();
    public QuizResponse quizResponse = new QuizResponse();

    public ResponseClass handleWithoutResponse(RequestClass request) {
        if (botCondition) {
            handleWithResponse(request);
        }
        else {
            String HandleRequest = request.getRequest();
            String[] req = HandleRequest.split(" ");
            if (req[0].equals("/start"))
            {
                botCondition = true;
                handleWithResponse(request);
            }
            else {
                responseClass.responseFlag = false;
            }

        }
        return responseClass;
    }

    public void handleWithResponse (RequestClass request){
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
            case "/nextqueastion":
                NextQuestion();
                break;
            case "/stop":
                Stop();
                break;
            default:
                if (dialogManager.CheckQuizGame()) { // поднят ли флаг игры
                    String response1;
                    if (dialogManager.CheckAnswer(req[0])){ // правильно ли человек написал ответ
                        response1 = dataResponse.trueAnswer; // правильно
                    }
                    else {
                        response1 = dataResponse.falseAnswer; // неправильно
                    }

                    String response2;
                    if (dialogManager.CheckNumberRemainQuiz(quizResponse.getQuizCount())) { // остались ли еще вопросы в копилке
                        quizResponse.UpdateQA(); // обновляем квиз, под капотом устанавливается quiz и answer
                        dialogManager.setWaitAnswer(quizResponse.getAnswer()); // установим то, какой ответ от пользователя ждем
                        response2 = quizResponse.getQuiz(); // следующий вопрос
                    }
                    else {
                        response2 = dataResponse.notRemainQuiz; // вопросов в копилке больше нет
                    }

                    responseClass.setResponse(response1 + "\n" + response2);

                }
                else {
                    responseClass.setResponse(dataResponse.nonsense); // пользователь ввел что-то не то

                }
                break;
        }
        responseClass.responseFlag = true;
    }

    private void Help() {
        responseClass.setResponse(dataResponse.help);
    }

    private void Start() {
        responseClass.setResponse(dataResponse.start);
        botCondition = true;
    }


    private void Quiz() {
        dialogManager.setQuizGame(true); // поднятие флага, что квиз начался
        dialogManager.ResetScore(); // обнулим счет прошлого квиза
        quizResponse.ResetQuiz(); // обнуляем квиз
        quizResponse.UpdateQA(); // обновляем квиз, под капотом устанавливается quiz и answer
        if (dialogManager.CheckNumberRemainQuiz(quizResponse.getQuizCount())) { // если еще остались квизы в копилке
            dialogManager.setWaitAnswer(quizResponse.getAnswer()); // установим то, какой ответ от пользователя ждем
            responseClass.setResponse(dataResponse.quiz + "\n" + quizResponse.getQuiz()); // начало квиза и первый вопрос
        }
        else {
            responseClass.setResponse(dataResponse.notRemainQuiz); // вопросов в копилке больше нет
        }
    }





    private void StopQuiz() {
        dialogManager.setQuizGame(false); // опускаем флаг игры
        dialogManager.ResetScore(); // обнулим счет квиза, который заканчиваем
        quizResponse.ResetQuiz(); // обнуляем квиз
        responseClass.setResponse(dataResponse.stopQuiz); // выводим, то что квиз остановлен
    }

    private void Score() {
        String score = dialogManager.GetScore(); // достаем счет из dialog manager в формате строки, состоящей из трех чисел, записанных через пробел
        String[] splitScore = score.split(" "); // разобьем на отдельные числа в массив строк
        responseClass.setResponse(dataResponse.TrueAnswerScore + splitScore[0] + "\n" +
                dataResponse.FalseAnswerScore + splitScore[1] + "\n" +
                dataResponse.AnswerScore + splitScore[2] + "\n"); // форматированный вывод
    }

    private void Restart() {
        Quiz(); // запустим просто заново квиз
    }

    private void Rereply() {

    }

    private void NextQuestion() {

    }

    private void Stop() {
        dialogManager.ResetScore();
        quizResponse.ResetQuiz();
        responseClass.setResponse(dataResponse.stop);
        botCondition = false;
    }
}
