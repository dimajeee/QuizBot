package org.example.DialogStatus;

import org.example.DataPack;
import org.example.DialogContext;

public class Quiz implements DialogStatus{
    DataPack dataPack;
    public Quiz(DataPack dataPack) {
        this.dataPack = dataPack;
    }


    @Override
    public String getDialogStatus() {
        return null;
    }

    @Override
    public void nextDialogStatus(DialogContext dialogContext) {
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
        dialogContext.setDialogStatus(new Start(dataPack));
    }

    @Override
    public void previousDialogStatus(DialogContext dialogContext) {
        dialogContext.setDialogStatus(new Quiz(dataPack));
    }
}
