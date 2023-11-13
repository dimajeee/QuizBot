package org.example.GameQuiz;

public class GameQuizClass implements GameQuiz {
    private int TrueAnswerScore;
    private int FalseAnswerScore;
    private int AnswerScore;
    private String WaitAnswer;



    private boolean QuizGame;
    public boolean getQuizGame() {
        return QuizGame;
    }
    private boolean RestartQuestion;

    public void setQuizGame(boolean quizGame) {QuizGame = quizGame;}


    public void setWaitAnswer(String waitAnswer) {
        WaitAnswer = waitAnswer;
    }

    public GameQuizClass() {
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
            return true;
        }
        return false;
    }
    public void DoCheckAnswer(String Answer) {
        if (CheckAnswer(Answer)) {
            TrueAnswer();
        }
        else {
            FalseAnswer();
        }

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
        String TAS = String.valueOf(TrueAnswerScore);
        String FAS = String.valueOf(FalseAnswerScore);
        String AS = String.valueOf(AnswerScore);
        return TAS + " " + FAS + " " + AS;
    }

    public void ResetScore() {
        AnswerScore = 0;
        TrueAnswerScore = 0;
        FalseAnswerScore = 0;
    }
}
