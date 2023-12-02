package org.example;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math;

public class QuizResponse{
    private ArrayList<QuizResponse> quizBase=new ArrayList<>();
    private int QuizCount;
    private int CurrentQuizNumber = 0;
    //private final String[][] quizTable;
    private String Quiz;
    private String CorrectAnswer;

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        CorrectAnswer = correctAnswer;
    }

    private String[] Answer=new String[4];
    private ArrayList<KeyboardRow> quizKeyboardrows=new ArrayList<>();

    public ArrayList<KeyboardRow> getQuizKeyboardrows() {
        return quizKeyboardrows;
    }

    public void setQuizKeyboardrows(ArrayList<KeyboardRow> quizKeyboardrows) {
        this.quizKeyboardrows = quizKeyboardrows;
    }

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
            Integer n;
            KeyboardRow row=new KeyboardRow();
            while ((line = bufferedReader.readLine()) != null){
                ArrayList<Integer> index=new ArrayList<>(4);
                index.add(0);
                index.add(1);
                index.add(2);
                index.add(3);
                QuizResponse quiz =new QuizResponse();
                quiz.Quiz=line;
                line= bufferedReader.readLine();
                quiz.Answer=line.split(" ");
                quiz.CorrectAnswer=quiz.Answer[0];
                System.out.println(quiz.CorrectAnswer);
                for (int i=0;i<2;i++) {
                    n=(int)(Math.random()*index.size());
                    System.out.println(index.get(n));
                    row.add(quiz.Answer[index.get(n)]);
                    index.remove(n);
                }
                quiz.quizKeyboardrows.add(row);
                row=new KeyboardRow();
                for (int i=2;i<4;i++) {
                    n=(int)(Math.random()*index.size());
                    row.add(quiz.Answer[n]);
                    index.remove(n);
                }
                quiz.quizKeyboardrows.add(row);
                row=new KeyboardRow();
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
        CorrectAnswer=quizBase.get(CurrentQuizNumber).CorrectAnswer;
        quizKeyboardrows=quizBase.get(CurrentQuizNumber).quizKeyboardrows;
        CurrentQuizNumber += 1;
    }

    public void ResetQuiz() {
        CurrentQuizNumber = 0;
    }


}
