package org.example;

public interface DialogManager {
    public void setQuizGame(boolean quizGame);
    public void setWaitAnswer(String waitAnswer);

    public boolean CheckAnswer(String Answer);

    public boolean CheckNumberRemainQuiz(int quizCount);

    public boolean CheckQuizGame();

}
