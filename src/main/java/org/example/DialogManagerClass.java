package org.example;

public class DialogManagerClass implements DialogManager {
    private int TrueAnswerScore;
    private int FalseAnswerScore;
    private int AnswerScore;
    private String WaitAnswer;
    private boolean QuizGame;
    private boolean RestartQuestion;

    public void setQuizGame(boolean quizGame) {
        QuizGame = quizGame;
    }


    public void setWaitAnswer(String waitAnswer) {
        WaitAnswer = waitAnswer;
    }

    public DialogManagerClass() {
        AnswerScore = 0;
        TrueAnswerScore = 0;
        FalseAnswerScore = 0;
        QuizGame = false;
    }

    private void TrueAnswer() {
        AnswerScore += 1;
        TrueAnswerScore += 1;
    }

    private void FalseAnswer() {
        AnswerScore += 1;
        FalseAnswerScore += 1;
    }

    @Override
    public boolean CheckAnswer(String Answer) {
        System.out.println(Answer);
        System.out.println(WaitAnswer);
        if (Answer.equals(WaitAnswer)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean CheckNumberRemainQuiz(int quizCount) {
        if (quizCount <= AnswerScore) {
            return false;
        }
        return true;
    }

    public boolean CheckQuizGame() {
        System.out.println(QuizGame);
        return QuizGame;
    }

    public String GetScore() {
        return "";
    }



}
