package org.example;

public class DialogManagerClass implements DialogManager{
    private int TrueAnswerScore;
    private int FalseAnswerScore;
    private int AnswerScore;
    public DialogManagerClass() {
        AnswerScore = 0;
        TrueAnswerScore = 0;
        FalseAnswerScore = 0;
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
    public void CheckAnswer() {

    }

    @Override
    public void CheckNumberRemainQuiz() {

    }


}
