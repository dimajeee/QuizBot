package org.example.DialogStatus;

import org.example.DialogContext;
import org.example.Person;
import org.example.DataResponse;
import org.example.Response;

public class NextQuiz implements DialogStatus{
    private final Person person;
    private final DataResponse dataResponse;
    private final Response response = new Response();
    public NextQuiz(Person person, DataResponse dataResponse) {
        this.person = person;
        this.dataResponse = dataResponse;
    }

    @Override
    public String getDialogStatus() {
        return null;
    }

    @Override
    public Response nextDialogStatus(DialogContext dialogContext, String[] req) {
        String response1;
        if (person.getGameQuiz().CheckAnswer(req[0])){ // правильно ли человек написал ответ
            person.getGameQuiz().DoCheckAnswer(req[0]);
            response1 = dataResponse.trueAnswer; // правильно
            String response2 = SecondResponse(dialogContext);
            response.setResponse(response1 + "\n" + response2);
        }
        else if (CheckCommand(req)) {
            DoCommand(dialogContext, req);
        }
        else {
            response1 = dataResponse.falseAnswer; // неправильно
            person.getGameQuiz().DoCheckAnswer(req[0]);
            String response2 = SecondResponse(dialogContext);
            response.setResponse(response1 + "\n" + response2);
        }
        return response;
    }

    @Override
    public void previousDialogStatus(DialogContext dialogContext) {}

    private boolean CheckCommand(String[] req) {
        return switch (req[0]) {
            case "/score", "/rereply", "/nextquestion" -> true;
            default -> false;
        };
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
                response.setResponse(dataResponse.nonsense);
                dialogContext.setDialogStatus(new Start(person, dataResponse));
                break;
        }
    }

    private String SecondResponse(DialogContext dialogContext) {
        String response;
        if (person.getQuizResponse().CheckRemainQuiz())
        { // остались ли еще вопросы в копилке
            person.getQuizResponse().UpdateQA(); // обновляем квиз, под капотом устанавливается quiz и answer
            person.getGameQuiz().setWaitAnswer(person.getQuizResponse().getAnswer()); // установим то, какой ответ от пользователя ждем
            response = person.getQuizResponse().getQuiz(); // следующий вопрос
            dialogContext.setDialogStatus(new NextQuiz(person, dataResponse));
        }
        else {
            response = dataResponse.notRemainQuiz; // вопросов в копилке больше нет
            dialogContext.setDialogStatus(new Start(person, dataResponse));
        }
        return response;
    }

    private void Rereply() {
        if (person.getGameQuiz().getQuizGame()) { // игра идет
            response.setResponse(person.getQuizResponse().getQuiz());
        }
        else {
            response.setResponse(dataResponse.notGameQuiz);
        }
    }

    private void NextQuestion() {
        if (person.getGameQuiz().getQuizGame()) {
            if (person.getQuizResponse().CheckRemainQuiz()) {
                person.getQuizResponse().UpdateQA(); // обновляем квиз, под капотом устанавливается quiz и answer
                person.getGameQuiz().setWaitAnswer(person.getQuizResponse().getAnswer()); // установим то, какой ответ от пользователя ждем
                response.setResponse(person.getQuizResponse().getQuiz()); // начало квиза и первый вопрос
            }
            else {
                response.setResponse(dataResponse.notRemainQuiz); // вопросов в копилке больше нет
            }
        }
        else {
            response.setResponse(dataResponse.notGameQuiz);
        }
    }

    private void Score() {
        String score = person.getGameQuiz().GetScore(); // достаем счет из dialog manager в формате строки, состоящей из трех чисел, записанных через пробел
        String[] splitScore = score.split(" "); // разобьем на отдельные числа в массив строк
        response.setResponse(dataResponse.TrueAnswerScore + splitScore[0] + "\n" +
                dataResponse.FalseAnswerScore + splitScore[1] + "\n" +
                dataResponse.AnswerScore + splitScore[2] + "\n"); // форматированный вывод
    }
}
