package org.example.DialogStatus;

import org.example.DialogContext;
import org.example.Person.PersonClass;
import org.example.Response.DataResponse;

public class Start implements DialogStatus{
    private PersonClass person;
    public DataResponse dataResponse;
    public Start(PersonClass person, DataResponse dataResponse) {
        this.person = person;
        this.dataResponse = dataResponse;
    }
    @Override
    public String getDialogStatus() {
        return "Start";
    }

    @Override
    public void nextDialogStatus(DialogContext dialogContext) {
        String HandleRequest = person.getRequestClass().getRequest();
        String[] req = HandleRequest.split(" ");
        switch (req[0]) {
            case "/help":
                person.getResponseClass().setResponse(dataResponse.help);
                dialogContext.setDialogStatus(new Start(person, dataResponse));
                break;
            case "/start":
                person.getResponseClass().setResponse(dataResponse.start);
                dialogContext.setDialogStatus(new Start(person, dataResponse));
                break;
            case "/quiz":
                Quiz();
                dialogContext.setDialogStatus(new NextQuiz(person, dataResponse));
                break;
            case "/stop":
                Stop();
                dialogContext.setDialogStatus(new Start(person, dataResponse));
                break;
            case "/stopquiz":
                StopQuiz();
                dialogContext.setDialogStatus(new Start(person, dataResponse));
                break;
            case "/score":
                Score();
                dialogContext.setDialogStatus(new Start(person, dataResponse));
                break;
            case "/nextquestion":
                NextQuestion();
                dialogContext.setDialogStatus(new NextQuiz(person, dataResponse));
                break;
            case "/rereply":
                Rereply();
                dialogContext.setDialogStatus(new NextQuiz(person, dataResponse));
                break;
            default:
                person.getResponseClass().setResponse(dataResponse.nonsense);
                dialogContext.setDialogStatus(new Start(person, dataResponse));
                break;
            }
    }



    @Override
    public void previousDialogStatus(DialogContext dialogContext) {
        dialogContext.setDialogStatus(new Start(person, dataResponse));
    }

    private void Quiz() {
        person.getGameQuizClass().setQuizGame(true); // поднятие флага, что квиз начался
        person.getGameQuizClass().ResetScore(); // обнулим счет прошлого квиза
        person.getQuizResponce().ResetQuiz(); // обнуляем квиз
        person.getQuizResponce().UpdateQA(); // обновляем квиз, под капотом устанавливается quiz и answer
        if (person.getGameQuizClass().CheckNumberRemainQuiz(person.getQuizResponce().getQuizCount())) { // если еще остались квизы в копилке
            person.getGameQuizClass().setWaitAnswer(person.getQuizResponce().getAnswer()); // установим то, какой ответ от пользователя ждем
            person.getResponseClass().setResponse(dataResponse.quiz + "\n" + person.getQuizResponce().getQuiz()); // начало квиза и первый вопрос
        }
        else {
            person.getResponseClass().setResponse(dataResponse.notRemainQuiz); // вопросов в копилке больше нет
        }
    }

    private void Stop() {
        person.getGameQuizClass().ResetScore();
        person.getQuizResponce().ResetQuiz();
        person.getResponseClass().setResponse(dataResponse.stop);
    }

    private void Score() {
        String score = person.getGameQuizClass().GetScore(); // достаем счет из dialog manager в формате строки, состоящей из трех чисел, записанных через пробел
        String[] splitScore = score.split(" "); // разобьем на отдельные числа в массив строк
        person.getResponseClass().setResponse(dataResponse.TrueAnswerScore + splitScore[0] + "\n" +
                dataResponse.FalseAnswerScore + splitScore[1] + "\n" +
                dataResponse.AnswerScore + splitScore[2] + "\n"); // форматированный вывод
    }

    private void NextQuestion() {
        if (person.getGameQuizClass().getQuizGame()) {
            boolean local = false;
            if (person.getQuizResponce().CheckRemainQuiz()) {
                person.getQuizResponce().UpdateQA(); // обновляем квиз, под капотом устанавливается quiz и answer
                local = true;
            }
            if (person.getGameQuizClass().CheckNumberRemainQuiz(person.getQuizResponce().getQuizCount()) && local) { // если еще остались квизы в копилке
                person.getGameQuizClass().setWaitAnswer(person.getQuizResponce().getAnswer()); // установим то, какой ответ от пользователя ждем
                person.getResponseClass().setResponse(person.getQuizResponce().getQuiz()); // начало квиза и первый вопрос
            }
            else {
                person.getResponseClass().setResponse(dataResponse.notRemainQuiz); // вопросов в копилке больше нет
            }
        }
        else {
            person.getResponseClass().setResponse(dataResponse.notGameQuiz);
        }
    }

    private void Rereply() {
        if (person.getGameQuizClass().getQuizGame()) { // игра идет
            person.getResponseClass().setResponse(person.getQuizResponce().getQuiz());
        }
        else {
            person.getResponseClass().setResponse(dataResponse.notGameQuiz);
        }
    }

    private void StopQuiz() {
        person.getGameQuizClass().setQuizGame(false); // опускаем флаг игры
        person.getGameQuizClass().ResetScore(); // обнулим счет квиза, который заканчиваем
        person.getQuizResponce().ResetQuiz(); // обнуляем квиз
        person.getResponseClass().setResponse(dataResponse.stopQuiz); // выводим, то что квиз остановлен
    }

}
