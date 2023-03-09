package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalquestionstextview;
    TextView questiontextview;
    Button ansa,ansb,ansc,ansd;
    Button submitbtn;
    int score=0;
    int totalquesion=Questionanswer.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalquestionstextview =findViewById(R.id.total_questions);
        questiontextview=findViewById(R.id.question);
        ansa=findViewById(R.id.ans_A);
        ansb=findViewById(R.id.ans_B);
        ansc=findViewById(R.id.ans_C);
        ansd=findViewById(R.id.ans_D);
        submitbtn=findViewById(R.id.submit_btn);
        ansa.setOnClickListener(this);
         ansb.setOnClickListener(this);
         ansc.setOnClickListener(this);
         ansd.setOnClickListener(this);
         submitbtn.setOnClickListener(this);
         totalquestionstextview.setText("Total questions :"+totalquesion);
         loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        ansa.setBackgroundColor(Color.WHITE);
        ansb.setBackgroundColor(Color.WHITE);
        ansc.setBackgroundColor(Color.WHITE);
        ansd.setBackgroundColor(Color.WHITE);
      Button clickedButton =(Button) view;
      if(clickedButton.getId()==R.id.submit_btn)
      {
          if(selectedAnswer.equals(Questionanswer.correctAnswer[currentQuestionIndex])){
              score++;
          }
       currentQuestionIndex++;
       loadNewQuestion();


      }
      else
      {
        //choices button clicked
        selectedAnswer =clickedButton.getText().toString();
        clickedButton.setBackgroundColor(Color.MAGENTA);
      }
    }
     void loadNewQuestion(){
        if(currentQuestionIndex==totalquesion)
        {
            finishQuiz();
            return;
        }
        questiontextview.setText(Questionanswer.question[currentQuestionIndex]);
        ansa.setText(Questionanswer.choices[currentQuestionIndex][0]);
        ansb.setText(Questionanswer.choices[currentQuestionIndex][1]);
        ansc.setText(Questionanswer.choices[currentQuestionIndex][2]);
        ansd.setText(Questionanswer.choices[currentQuestionIndex][3]);

     }
     void finishQuiz()
     {
         String passStatus="";
         if(score>totalquesion*0.60) {
             passStatus="Passed";
         }else {
             passStatus="Failed";
         }
         new  AlertDialog.Builder(this).setTitle(passStatus).setMessage("Score is "+ score+" out of "+totalquesion)
                 .setPositiveButton("Restart",((dialogInterface, i) -> restartQuiz())).setCancelable(false).show();
     }
      void restartQuiz()
      {
          score=0;
          currentQuestionIndex=0;
          loadNewQuestion();
      }
}