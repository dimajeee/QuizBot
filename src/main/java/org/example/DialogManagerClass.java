package org.example;

public class DialogManagerClass implements DialogManager {
    private int TrueAnswerScore;
    private int FalseAnswerScore;
    private int AnswerScore;
    private String WaitAnswer;
    private boolean QuizGame;
    private boolean RestartQuestion;

    public void setQuizGame(boolean quizGame) {QuizGame = quizGame;}


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
        if (Answer.equals(WaitAnswer)) {
            TrueAnswer();
            return true;
        }
        FalseAnswer();
        return false;
    }

    @Override
    public boolean CheckNumberRemainQuiz(int quizCount) {
        if (quizCount <= AnswerScore) {
            return false;
        }
        return true;
    }

    public boolean CheckQuizGame() {return QuizGame;}

    public String GetScore() {
        String TASS = String.valueOf(TrueAnswerScore);
        String FASS = String.valueOf(FalseAnswerScore);
        String ASS = String.valueOf(AnswerScore);
        return TASS + " " + FASS + " " + ASS;
    }

    public void ResetScore() {
        AnswerScore = 0;
        TrueAnswerScore = 0;
        FalseAnswerScore = 0;
    }
}
