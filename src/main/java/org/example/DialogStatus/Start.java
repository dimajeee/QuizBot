package org.example.DialogStatus;

import org.example.DialogContext;
import org.example.Person;
import org.example.DataResponse;
import org.example.Response;

public class Start implements DialogStatus{
    private final Person person;
    private final DataResponse dataResponse;

    public Start(Person person, DataResponse dataResponse) {
        this.person = person;
        this.dataResponse = dataResponse;
    }

    private Response response;
    @Override
    public String getDialogStatus() {
        return "Start";
    }
    @Override
    public Response nextDialogStatus(DialogContext dialogContext, String[] req) {
        response = new Response();
        switch (req[0]) {
            case "/help":
                response.setResponse(dataResponse.help);
                dialogContext.setDialogStatus(new Start(person, dataResponse));
                break;
            case "/start":
                response.setResponse(dataResponse.start);
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
                response.setResponse(dataResponse.nonsense);
                dialogContext.setDialogStatus(new Start(person, dataResponse));
                break;
            }
            return response;
    }

    @Override
    public void previousDialogStatus(DialogContext dialogContext) {
        dialogContext.setDialogStatus(new Start(person, dataResponse));
    }

    private void Quiz() {
        person.getGameQuiz().setQuizGame(true); // поднятие флага, что квиз начался
        person.getGameQuiz().ResetScore(); // обнулим счет прошлого квиза
        person.getQuizResponse().ResetQuiz(); // обнуляем квиз
        if (person.getQuizResponse().CheckRemainQuiz()) { // если еще остались квизы в копилке
            person.getQuizResponse().UpdateQA(); // обновляем квиз, под капотом устанавливается quiz и answer
            person.getGameQuiz().setWaitAnswer(person.getQuizResponse().getAnswer()); // установим то, какой ответ от пользователя ждем
            response.setResponse(dataResponse.quiz + "\n" + person.getQuizResponse().getQuiz()); // начало квиза и первый вопрос
        }
        else {
            response.setResponse(dataResponse.notRemainQuiz); // вопросов в копилке больше нет
        }
    }

    private void Stop() {
        person.getGameQuiz().ResetScore();
        person.getQuizResponse().ResetQuiz();
        response.setResponse(dataResponse.stop);
    }

    private void Score() {
        String score = person.getGameQuiz().GetScore(); // достаем счет из dialog manager в формате строки, состоящей из трех чисел, записанных через пробел
        String[] splitScore = score.split(" "); // разобьем на отдельные числа в массив строк
        response.setResponse(dataResponse.TrueAnswerScore + splitScore[0] + "\n" +
                dataResponse.FalseAnswerScore + splitScore[1] + "\n" +
                dataResponse.AnswerScore + splitScore[2] + "\n"); // форматированный вывод
    }

    private void NextQuestion() {
        if (person.getGameQuiz().getQuizGame()) {
            boolean local = false;
            if (person.getQuizResponse().CheckRemainQuiz()) {
                person.getQuizResponse().UpdateQA(); // обновляем квиз, под капотом устанавливается quiz и answer
                local = true;
            }
            if (person.getGameQuiz().CheckNumberRemainQuiz(person.getQuizResponse().getQuizCount()) && local) { // если еще остались квизы в копилке
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

    private void Rereply() {
        if (person.getGameQuiz().getQuizGame()) { // игра идет
            response.setResponse(person.getQuizResponse().getQuiz());
        }
        else {
            response.setResponse(dataResponse.notGameQuiz);
        }
    }

    private void StopQuiz() {
        person.getGameQuiz().setQuizGame(false); // опускаем флаг игры
        person.getGameQuiz().ResetScore(); // обнулим счет квиза, который заканчиваем
        person.getQuizResponse().ResetQuiz(); // обнуляем квиз
        response.setResponse(dataResponse.stopQuiz); // выводим, то что квиз остановлен
    }

}
