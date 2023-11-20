package org.example;

public class GameQuiz {
    private int TrueAnswerScore;
    private int FalseAnswerScore;
    private int AnswerScore;
    private String[] WaitAnswer=new String[10];


    private boolean QuizGame;
    public boolean getQuizGame() {
        return QuizGame;
    }

    public void setQuizGame(boolean quizGame) {QuizGame = quizGame;}


    public void setWaitAnswer(String[] waitAnswer) {
        WaitAnswer = waitAnswer;
    }

    public GameQuiz() {
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

    public boolean CheckAnswer(String Answer) {
        for (int i=0; i<2;i++) {
            if (Answer.equals(WaitAnswer[i])) {
                return true;
            }
        }
        System.out.println("неверное");
        return false;
    }
    public void DoCheckAnswer(String Answer) {
        if (CheckAnswer(Answer)) {TrueAnswer();}
        else {
            System.out.println("жопа");
            FalseAnswer();}
    }

    public boolean CheckNumberRemainQuiz(int quizCount) {
        return quizCount > AnswerScore;
    }

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
