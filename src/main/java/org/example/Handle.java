package org.example;


import org.example.DialogStatus.Start;
import org.example.Response.ResponseClass;

public class Handle {
    DialogContext dialogContext;
    DataPack dataPack;
    private boolean botCondition = false;

    public Handle() {
        dataPack = new DataPack();
        dialogContext = new DialogContext();
        dialogContext.setDialogStatus(new Start(dataPack));
    }

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

    public void handleWithResponse () {
        String[] req = dataPack.requestClass.getRequest().split(" ");
        if (CheckCommand(req[0])) {
            dialogContext.setDialogStatus(new Start(dataPack));
        }
        dialogContext.nextDialogContext();
        dataPack.responseClass.responseFlag = true;
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
//
//    private void Restart() {
//        Quiz(); // запустим просто заново квиз
//    }

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

    private boolean CheckCommand(String req) {
        String commands = "/start" + "/stop" + "/score" + "/help" + "/quiz";
        return commands.contains(req);
    }


}
