package org.example;


import org.example.DialogStatus.Start;
import org.example.GameQuiz.GameQuizClass;
import org.example.GameQuizResponse.QuizResponse;
import org.example.Response.DataResponse;
import org.example.Response.ResponseClass;
import org.example.Request.RequestClass;

public class Handle {
    public Handle() {
        dataPack = new DataPack();
        dialogContext = new DialogContext();
        dialogContext.setDialogStatus(new Start(dataPack));
    }


    DialogContext dialogContext;
    DataPack dataPack;

    private boolean botCondition = false;
//    public ResponseClass responseClass = new ResponseClass();
//    public DataResponse dataResponse = new DataResponse();
//    public GameQuizClass gameQuizClass = new GameQuizClass();
//    public QuizResponse quizResponse = new QuizResponse();

    public ResponseClass handleWithoutResponse() {
        if (botCondition) {
            handleWithResponse();
        }
        else {
            String HandleRequest = dataPack.requestClass.getRequest();
            String[] req = HandleRequest.split(" ");
            if (req[0].equals("/start"))
            {
                botCondition = true;
                handleWithResponse();
            }
            else if (req[0].equals("/stop")) {
                botCondition = false;
                handleWithResponse();
            }
            else {
                dataPack.responseClass.responseFlag = false;
            }

        }
        return dataPack.responseClass;
    }

    public void handleWithResponse (){
        dialogContext.nextDialogContext();
//        String HandleRequest = dataPack.requestClass.getRequest();
//        String[] req = HandleRequest.split(" ");
//        switch (req[0]){
//            case "/start":
//                Start();
//                break;
//            case "/help":
//                Help();
//                break;
//            case "/quiz":
//                Quiz();
//                break;
//            case "/stopquiz":
//                StopQuiz();
//                break;
//            case "/score":
//                Score();
//                break;
//            case "/restartquiz":
//                Restart();
//                break;
//            case "/rereply":
//                Rereply();
//                break;
//            case "/nextqueastion":
//                NextQuestion();
//                break;
//            case "/stop":
//                Stop();
//                break;
//            default:
//                if (dataPack.gameQuizClass.CheckQuizGame()) { // поднят ли флаг игры
//                    String response1;
//                    if (dataPack.gameQuizClass.CheckAnswer(req[0])){ // правильно ли человек написал ответ
//                        response1 = dataPack.dataResponse.trueAnswer; // правильно
//                    }
//                    else {
//                        response1 = dataPack.dataResponse.falseAnswer; // неправильно
//                    }
//
//                    String response2;
//                    if (dataPack.gameQuizClass.CheckNumberRemainQuiz(dataPack.quizResponse.getQuizCount())) { // остались ли еще вопросы в копилке
//                        dataPack.quizResponse.UpdateQA(); // обновляем квиз, под капотом устанавливается quiz и answer
//                        dataPack.gameQuizClass.setWaitAnswer(dataPack.quizResponse.getAnswer()); // установим то, какой ответ от пользователя ждем
//                        response2 = dataPack.quizResponse.getQuiz(); // следующий вопрос
//                    }
//                    else {
//                        response2 = dataPack.dataResponse.notRemainQuiz; // вопросов в копилке больше нет
//                    }
//
//                    dataPack.responseClass.setResponse(response1 + "\n" + response2);
//
//                }
//                else {
//                    dataPack.responseClass.setResponse(dataPack.dataResponse.nonsense); // пользователь ввел что-то не то
//
//                }
//                break;
//        }
        dataPack.responseClass.responseFlag = true;
    }

    private void Help() {
        dataPack.responseClass.setResponse(dataPack.dataResponse.help);
    }

    private void Start() {
        dataPack.responseClass.setResponse(dataPack.dataResponse.start);
        botCondition = true;
    }


    private void Quiz() {
        dataPack.gameQuizClass.setQuizGame(true); // поднятие флага, что квиз начался
        dataPack.gameQuizClass.ResetScore(); // обнулим счет прошлого квиза
        dataPack.quizResponse.ResetQuiz(); // обнуляем квиз
        dataPack.quizResponse.UpdateQA(); // обновляем квиз, под капотом устанавливается quiz и answer
        if (dataPack.gameQuizClass.CheckNumberRemainQuiz(dataPack.quizResponse.getQuizCount())) { // если еще остались квизы в копилке
            dataPack.gameQuizClass.setWaitAnswer(dataPack.quizResponse.getAnswer()); // установим то, какой ответ от пользователя ждем
            dataPack.responseClass.setResponse(dataPack.dataResponse.quiz + "\n" + dataPack.quizResponse.getQuiz()); // начало квиза и первый вопрос
        }
        else {
            dataPack.responseClass.setResponse(dataPack.dataResponse.notRemainQuiz); // вопросов в копилке больше нет
        }
    }





    private void StopQuiz() {
        dataPack.gameQuizClass.setQuizGame(false); // опускаем флаг игры
        dataPack.gameQuizClass.ResetScore(); // обнулим счет квиза, который заканчиваем
        dataPack.quizResponse.ResetQuiz(); // обнуляем квиз
        dataPack.responseClass.setResponse(dataPack.dataResponse.stopQuiz); // выводим, то что квиз остановлен
    }

    private void Score() {
        String score = dataPack.gameQuizClass.GetScore(); // достаем счет из dialog manager в формате строки, состоящей из трех чисел, записанных через пробел
        String[] splitScore = score.split(" "); // разобьем на отдельные числа в массив строк
        dataPack.responseClass.setResponse(dataPack.dataResponse.TrueAnswerScore + splitScore[0] + "\n" +
                dataPack.dataResponse.FalseAnswerScore + splitScore[1] + "\n" +
                dataPack.dataResponse.AnswerScore + splitScore[2] + "\n"); // форматированный вывод
    }

    private void Restart() {
        Quiz(); // запустим просто заново квиз
    }

    private void Rereply() {

    }

    private void NextQuestion() {

    }

    private void Stop() {
        dataPack.gameQuizClass.ResetScore();
        dataPack.quizResponse.ResetQuiz();
        dataPack.responseClass.setResponse(dataPack.dataResponse.stop);
        botCondition = false;
    }
}
