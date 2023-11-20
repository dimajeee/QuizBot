package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuizResponse{
    private ArrayList<QuizResponse> quizBase=new ArrayList<>();
    private int QuizCount;
    private int CurrentQuizNumber = 0;
    //private final String[][] quizTable;
    private String Quiz;
    private String[] Answer=new String[10];
    public int getQuizCount() {
        return QuizCount;
    }

    public String getQuiz() {
        return Quiz;
    }

    public String[] getAnswer() {
        return Answer;
    }





    public void updateQuizBase (){
        try {
            FileReader fileReader = new FileReader( "question.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null){
                QuizResponse quiz =new QuizResponse();
                quiz.Quiz=line;
                line= bufferedReader.readLine();
                quiz.Answer=line.split(" ");
                quizBase.add(quiz);
            }
        }
        catch (IOException e){
            System.out.println("Ошибка при чтении файла");
            e.printStackTrace();
        }
        for (QuizResponse q:quizBase){
            System.out.println(q.Quiz);

        }
        QuizCount = quizBase.size();
    }
    public QuizResponse() {
        //quizBase=new ArrayList<>();
        //updateQuizBase();
    }
    public boolean CheckRemainQuiz() {
        return CurrentQuizNumber < QuizCount;
    }

    public void UpdateQA() {
        Quiz = quizBase.get(CurrentQuizNumber).Quiz;
        Answer = quizBase.get(CurrentQuizNumber).Answer;
        CurrentQuizNumber += 1;
    }

    public void ResetQuiz() {
        CurrentQuizNumber = 0;
    }


}
