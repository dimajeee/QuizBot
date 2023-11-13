package org.example.DialogStatus;

import org.example.DataPack;
import org.example.DialogContext;

public class NextQuiz implements DialogStatus{
    DataPack dataPack;
    public NextQuiz(DataPack dataPack) {
        this.dataPack = dataPack;
    }

    @Override
    public String getDialogStatus() {
        return null;
    }

    @Override
    public void nextDialogStatus(DialogContext dialogContext) {
        String HandleRequest = dataPack.requestClass.getRequest();
        String[] req = HandleRequest.split(" ");
        String response1;
        if (dataPack.gameQuizClass.CheckAnswer(req[0])){ // правильно ли человек написал ответ
            dataPack.gameQuizClass.DoCheckAnswer(req[0]);
            response1 = dataPack.dataResponse.trueAnswer; // правильно
            String response2 = SecondResponse(dialogContext);
            dataPack.responseClass.setResponse(response1 + "\n" + response2);
        }
//        else if (CheckCommand(req[0])) {
//            DoCommand(dialogContext, req);
//        }
        else {
            response1 = dataPack.dataResponse.falseAnswer; // неправильно
            String response2 = SecondResponse(dialogContext);
            dataPack.responseClass.setResponse(response1 + "\n" + response2);
        }


    }

    @Override
    public void previousDialogStatus(DialogContext dialogContext) {

    }

    private boolean CheckCommand(String req) {
        String commands = "/start" + "/stop" + "/score" + "/help" + "/quiz";
        return commands.contains(req);
    }
    private void DoCommand(DialogContext dialogContext, String[] req) {
        switch (req[0]) {
            case "/help":
                dataPack.responseClass.setResponse(dataPack.dataResponse.help);
                dialogContext.setDialogStatus(new Start(dataPack));
                break;
            case "/start":
                dataPack.responseClass.setResponse(dataPack.dataResponse.start);
                dialogContext.setDialogStatus(new Start(dataPack));
                break;
            case "/quiz":
                Quiz();
//                dataPack.responseClass.setResponse(dataPack.dataResponse.quiz);
                dialogContext.setDialogStatus(new NextQuiz(dataPack));
                break;
            case "/stop":
                Stop();
                dialogContext.setDialogStatus(new Start(dataPack));
                break;
            case "/score":
                Score();
                break;
            default:
                dataPack.responseClass.setResponse(dataPack.dataResponse.nonsense);
                dialogContext.setDialogStatus(new Start(dataPack));
                break;
        }
    }



    private String SecondResponse(DialogContext dialogContext) {
        String response;
        if (dataPack.gameQuizClass.CheckNumberRemainQuiz(dataPack.quizResponse.getQuizCount())) { // остались ли еще вопросы в копилке
            dataPack.quizResponse.UpdateQA(); // обновляем квиз, под капотом устанавливается quiz и answer
            dataPack.gameQuizClass.setWaitAnswer(dataPack.quizResponse.getAnswer()); // установим то, какой ответ от пользователя ждем
            response = dataPack.quizResponse.getQuiz(); // следующий вопрос
            dialogContext.setDialogStatus(new NextQuiz(dataPack));
        }
        else {
            response = dataPack.dataResponse.notRemainQuiz; // вопросов в копилке больше нет
            dialogContext.setDialogStatus(new Start(dataPack));
        }
        return response;
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

    private void Stop() {
        dataPack.gameQuizClass.ResetScore();
        dataPack.quizResponse.ResetQuiz();
        dataPack.responseClass.setResponse(dataPack.dataResponse.stop);
    }

    private void Score() {
        String score = dataPack.gameQuizClass.GetScore(); // достаем счет из dialog manager в формате строки, состоящей из трех чисел, записанных через пробел
        String[] splitScore = score.split(" "); // разобьем на отдельные числа в массив строк
        dataPack.responseClass.setResponse(dataPack.dataResponse.TrueAnswerScore + splitScore[0] + "\n" +
                dataPack.dataResponse.FalseAnswerScore + splitScore[1] + "\n" +
                dataPack.dataResponse.AnswerScore + splitScore[2] + "\n"); // форматированный вывод
    }

}
