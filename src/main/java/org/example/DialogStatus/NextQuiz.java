package org.example.DialogStatus;

import org.example.*;

import java.util.ArrayList;

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
    public Response nextDialogStatus(DialogContext dialogContext, String req) {
        String response1;

        if (person.getGameQuiz().CheckAnswer(req)){// правильно ли человек написал ответ
            System.out.println("даа");
            person.getGameQuiz().DoCheckAnswer(req);
            response1 = dataResponse.trueAnswer; // правильно
            QuizResponse quizResponse=SecondResponse(dialogContext);
            if (quizResponse!=null) {
                String response2 = quizResponse.getQuiz();
                response.setResponse(response1 + "\n" + response2);
                response.setKeyboardRows(quizResponse.getQuizKeyboardrows());
            }
            else {
                String response2=dataResponse.notRemainQuiz;
                response.setResponse(response1 + "\n" + response2);
                response.setKeyboardRows(new ArrayList<>());
            }
        }
        else if (CheckCommand(req)) {
            DoCommand(dialogContext, req);
        }
        else {
            response1 = dataResponse.falseAnswer; // неправильно
            QuizResponse quizResponse=SecondResponse(dialogContext);
            person.getGameQuiz().DoCheckAnswer(req);
            if (quizResponse!=null) {
                String response2 = quizResponse.getQuiz();
                response.setResponse(response1 + "\n" + response2);
                response.setKeyboardRows(quizResponse.getQuizKeyboardrows());
            }
            else {
                String response2=dataResponse.notRemainQuiz;
                response.setResponse(response1 + "\n" + response2);
                response.setKeyboardRows(null);
            }
        }
        return response;
    }

    @Override
    public void previousDialogStatus(DialogContext dialogContext) {}

    private boolean CheckCommand(String req) {
        return switch (req) {
            case "/score", "/rereply", "/nextquestion" -> true;
            default -> false;
        };
    }
    private void DoCommand(DialogContext dialogContext, String req) {
        switch (req) {
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

    private QuizResponse SecondResponse(DialogContext dialogContext) {

        if (person.getQuizResponse().CheckRemainQuiz())
        { // остались ли еще вопросы в копилке
            person.getQuizResponse().UpdateQA(); // обновляем квиз, под капотом устанавливается quiz и answer
            person.getGameQuiz().setWaitAnswer(person.getQuizResponse().getCorrectAnswer()); // установим то, какой ответ от пользователя ждем
            System.out.println("wait answe: "+person.getGameQuiz().getWaitAnswer());
            dialogContext.setDialogStatus(new NextQuiz(person, dataResponse));
            QuizResponse resp = person.getQuizResponse(); // следующий вопрос

            return resp;
        }
        else {
            //response = dataResponse.notRemainQuiz; // вопросов в копилке больше нет
            dialogContext.setDialogStatus(new Start(person, dataResponse));
            return null;
        }

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
                person.getGameQuiz().setWaitAnswer(person.getQuizResponse().getCorrectAnswer()); // установим то, какой ответ от пользователя ждем
                response.setResponse(person.getQuizResponse().getQuiz()); // начало квиза и первый вопрос
                response.setKeyboardRows(person.getQuizResponse().getQuizKeyboardrows());
            }
            else {
                response.setResponse(dataResponse.notRemainQuiz);// вопросов в копилке больше нет
                response.setKeyboardRows(null);
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
