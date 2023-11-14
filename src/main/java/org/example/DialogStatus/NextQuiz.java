package org.example.DialogStatus;

import org.example.DialogContext;
import org.example.Person.PersonClass;
import org.example.Response.DataResponse;

public class NextQuiz implements DialogStatus{
    private PersonClass person;
    private DataResponse dataResponse;
    public NextQuiz(PersonClass person, DataResponse dataResponse) {
        this.person = person;
        this.dataResponse = dataResponse;
    }

    @Override
    public String getDialogStatus() {
        return null;
    }

    @Override
    public void nextDialogStatus(DialogContext dialogContext) {
        String HandleRequest = person.getRequestClass().getRequest();
        String[] req = HandleRequest.split(" ");
        String response1;
        if (person.getGameQuizClass().CheckAnswer(req[0])){ // правильно ли человек написал ответ
            person.getGameQuizClass().DoCheckAnswer(req[0]);
            response1 = dataResponse.trueAnswer; // правильно
            String response2 = SecondResponse(dialogContext);
            person.getResponseClass().setResponse(response1 + "\n" + response2);
        }
        else if (CheckCommand(req[0])) {
            DoCommand(dialogContext, req);
        }
        else {
            response1 = dataResponse.falseAnswer; // неправильно
            String response2 = SecondResponse(dialogContext);
            person.getResponseClass().setResponse(response1 + "\n" + response2);
        }


    }

    @Override
    public void previousDialogStatus(DialogContext dialogContext) {

    }

    private boolean CheckCommand(String req) {
        String commands = "/nextquestion" + "/rereply" + "/score";
        return commands.contains(req);
    }
    private void DoCommand(DialogContext dialogContext, String[] req) {
        switch (req[0]) {
            case "/score":
                Score();
                dialogContext.setDialogStatus(new Start(person, dataResponse));
                break;
            case "/rereply":
                Rereply();
                break;
            case "/nextquestion":
                NextQuestion();
                break;
            default:
                person.getResponseClass().setResponse(dataResponse.nonsense);
                dialogContext.setDialogStatus(new Start(person, dataResponse));
                break;
        }
    }

    private String SecondResponse(DialogContext dialogContext) {
        String response;
        if (person.getQuizResponce().CheckRemainQuiz())
        { // остались ли еще вопросы в копилке
            person.getQuizResponce().UpdateQA(); // обновляем квиз, под капотом устанавливается quiz и answer
            person.getGameQuizClass().setWaitAnswer(person.getQuizResponce().getAnswer()); // установим то, какой ответ от пользователя ждем
            response = person.getQuizResponce().getQuiz(); // следующий вопрос
            dialogContext.setDialogStatus(new NextQuiz(person, dataResponse));
        }
        else {
            response = dataResponse.notRemainQuiz; // вопросов в копилке больше нет
            dialogContext.setDialogStatus(new Start(person, dataResponse));
        }
        return response;
    }

    private void Rereply() {
        if (person.getGameQuizClass().getQuizGame()) { // игра идет
            person.getResponseClass().setResponse(person.getQuizResponce().getQuiz());
        }
        else {
            person.getResponseClass().setResponse(dataResponse.notGameQuiz);
        }
    }

    private void NextQuestion() {
        if (person.getGameQuizClass().getQuizGame()) {
            if (person.getQuizResponce().CheckRemainQuiz()) {
                person.getQuizResponce().UpdateQA(); // обновляем квиз, под капотом устанавливается quiz и answer
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

    private void Score() {
        String score = person.getGameQuizClass().GetScore(); // достаем счет из dialog manager в формате строки, состоящей из трех чисел, записанных через пробел
        String[] splitScore = score.split(" "); // разобьем на отдельные числа в массив строк
        person.getResponseClass().setResponse(dataResponse.TrueAnswerScore + splitScore[0] + "\n" +
                dataResponse.FalseAnswerScore + splitScore[1] + "\n" +
                dataResponse.AnswerScore + splitScore[2] + "\n"); // форматированный вывод
    }
}
