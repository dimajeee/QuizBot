package org.example.DialogStatus;

import org.example.DataPack;
import org.example.DialogContext;

public class Start implements DialogStatus{
    DataPack dataPack;
    public Start(DataPack dataPack) {
        this.dataPack = dataPack;
    }
    @Override
    public String getDialogStatus() {
        return "Start";
    }

    @Override
    public void nextDialogStatus(DialogContext dialogContext) {
        String HandleRequest = dataPack.requestClass.getRequest();
        String[] req = HandleRequest.split(" ");
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
            case "/nextquestion":
                NextQuestion();
                break;
            default:
                dataPack.responseClass.setResponse(dataPack.dataResponse.nonsense);
                dialogContext.setDialogStatus(new Start(dataPack));
                break;
            }
    }



    @Override
    public void previousDialogStatus(DialogContext dialogContext) {
        dialogContext.setDialogStatus(new Start(dataPack));
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

    private void NextQuestion() {
        if (dataPack.gameQuizClass.getQuizGame()) {
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
        else {

        }
    }
}
