package org.example;

public class DialogManagerClass implements DialogManager{
    public int TrueAnswerScore;
    public int FalseAnswerScore;
    public int AnswerScore;
    public DialogManagerClass() {
        AnswerScore = 0;
        TrueAnswerScore = 0;
        FalseAnswerScore = 0;
    }

    public void TrueAnswer() {
        AnswerScore += 1;
        TrueAnswerScore += 1;
    }

    public void FalseAnswer() {
        AnswerScore += 1;
        FalseAnswerScore += 1;
    }

    public void CheckAnswer()
    {

    }


}
