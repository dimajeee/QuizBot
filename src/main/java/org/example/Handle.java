package org.example;


import org.example.DialogStatus.Start;
import org.example.Person.PersonClass;
import org.example.Request.RequestClass;
import org.example.Response.DataResponse;
import org.example.Response.ResponseClass;

public class Handle {
    DataResponse dataResponse;
    public Handle() {
        dataResponse = new DataResponse();
    }

    public void handleWithoutResponse(PersonClass person) {
        if (person.getBotCondition()) {
            handleWithResponse(person);
        }
        else {
            String HandleRequest = person.getRequestClass().getRequest();
            String[] req = HandleRequest.split(" ");
            if (req[0].equals("/start"))
            {
                person.setBotCondition(true);
                handleWithResponse(person);
            }
            else if (req[0].equals("/stop")) {
                person.setBotCondition(false);
                handleWithResponse(person);
            }
            else {
                person.getResponseClass().responseFlag = false;
            }
        }
    }

    public void handleWithResponse (PersonClass person) {
        String[] req = person.getRequestClass().getRequest().split(" ");
        if (CheckCommand(req[0])) {
            person.dialogContext.setDialogStatus(new Start(person, dataResponse));
        }
        person.dialogContext.nextDialogContext();
        person.getResponseClass().responseFlag = true;
    }

//    private void StopQuiz() {
//        dataPack.gameQuizClass.setQuizGame(false); // опускаем флаг игры
//        dataPack.gameQuizClass.ResetScore(); // обнулим счет квиза, который заканчиваем
//        dataPack.quizResponse.ResetQuiz(); // обнуляем квиз
//        responseClass.setResponse(dataPack.dataResponse.stopQuiz); // выводим, то что квиз остановлен
//    }
//
//    private void Score() {
//        String score = dataPack.gameQuizClass.GetScore(); // достаем счет из dialog manager в формате строки, состоящей из трех чисел, записанных через пробел
//        String[] splitScore = score.split(" "); // разобьем на отдельные числа в массив строк
//        responseClass.setResponse(dataPack.dataResponse.TrueAnswerScore + splitScore[0] + "\n" +
//                dataPack.dataResponse.FalseAnswerScore + splitScore[1] + "\n" +
//                dataPack.dataResponse.AnswerScore + splitScore[2] + "\n"); // форматированный вывод
//    }
////
////    private void Restart() {
////        Quiz(); // запустим просто заново квиз
////    }
//
//    private void Rereply() {
//
//    }
//
//    private void NextQuestion() {
//
//    }
//
//    private void Stop() {
//        dataPack.gameQuizClass.ResetScore();
//        dataPack.quizResponse.ResetQuiz();
//        responseClass.setResponse(dataPack.dataResponse.stop);
//        botCondition = false;
//    }

    private boolean CheckCommand(String req) {
        String commands = "/start" + "/stop" + "/help" + "/quiz" + "/stopquiz" + "/restartquiz";
        return commands.contains(req);
    }


}
