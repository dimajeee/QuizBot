package org.example.DialogStatus;

import org.example.DataPack;
import org.example.DialogContext;
import org.example.Response.ResponseClass;

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
            response1 = dataPack.dataResponse.trueAnswer; // правильно
        }
        else {
            response1 = dataPack.dataResponse.falseAnswer; // неправильно
        }

        String response2;
        if (dataPack.gameQuizClass.CheckNumberRemainQuiz(dataPack.quizResponse.getQuizCount())) { // остались ли еще вопросы в копилке
            dataPack.quizResponse.UpdateQA(); // обновляем квиз, под капотом устанавливается quiz и answer
            dataPack.gameQuizClass.setWaitAnswer(dataPack.quizResponse.getAnswer()); // установим то, какой ответ от пользователя ждем
            response2 = dataPack.quizResponse.getQuiz(); // следующий вопрос
            dialogContext.setDialogStatus(new NextQuiz(dataPack));
        }
        else {
            response2 = dataPack.dataResponse.notRemainQuiz; // вопросов в копилке больше нет
            dialogContext.setDialogStatus(new Start(dataPack));
        }

        dataPack.responseClass.setResponse(response1 + "\n" + response2);
    }

    @Override
    public void previousDialogStatus(DialogContext dialogContext) {

    }
}
