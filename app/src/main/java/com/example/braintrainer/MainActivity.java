package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgain;
    TextView sumTextview;
    TextView timez;
    TextView rite;
    TextView wrang;
    TextView res;


    ArrayList<Float> answers=new ArrayList<Float>();
    ArrayList<Integer> highs=new ArrayList<Integer>();





    public void newQuestion(){

        String operatorSwitch;
        float c=0;
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        int operator = rand.nextInt(4);

        switch (operator){

            case 0: operatorSwitch= "+";
                c = a+b;
                break;
            case 1: operatorSwitch= "-";
                c = a-b;
                break;
            case 2: operatorSwitch= "*";
                c = a*b;
                break;
            case 3: operatorSwitch= "/";
                c = a/b;
                break;
            default: operatorSwitch= "";
        }



        sumTextview.setText(Integer.toString(a)+operatorSwitch+Integer.toString(b));

        locationOfCorrectAns=rand.nextInt(4);

        answers.clear();

        float wrongans;

        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAns){
                answers.add(c);
            }else{

                wrongans=rand.nextInt(41);
                while(wrongans==c){
                    wrongans=rand.nextInt(41);
                }
                answers.add(wrongans);
            }


        }


        button1.setText(Double.toString(answers.get(0)));
        button2.setText(Double.toString(answers.get(1)));
        button3.setText(Double.toString(answers.get(2)));
        button4.setText(Double.toString(answers.get(3)));







    }


    int locationOfCorrectAns;
    int rt,wr=0;

    public void choice(View view){
        Log.i("Tag",(String) view.getTag());
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAns))){

             res=(TextView)findViewById(R.id.result);
            res.setText("Correct");
            rt++;
            rite=(TextView)findViewById(R.id.right);
            rite.setText("RIGHT:"+Integer.toString(rt));

        }
        else{
             res=(TextView)findViewById(R.id.result);
            res.setText("Wrong");
            wr++;
            wrang=(TextView)findViewById(R.id.wrong);
            wrang.setText("WRONG:"+Integer.toString(wr));
        }


        newQuestion();



    }


   public void playag(View view){

    App.refresh(this);

   }






    public void start(View view){

        ImageView startButton=(ImageView)findViewById(R.id.sb);
        startButton.setVisibility(View.INVISIBLE);
        TextView startText=(TextView)findViewById(R.id.starttext);
        startText.setVisibility(View.INVISIBLE);



        button1=(Button)findViewById(R.id.an1);
        button2=(Button)findViewById(R.id.an2);
        button3=(Button)findViewById(R.id.an3);
        button4=(Button)findViewById(R.id.an4);
        playAgain=(Button)findViewById(R.id.playagain);
        rite=(TextView)findViewById(R.id.right);
        wrang=(TextView)findViewById(R.id.wrong);
        sumTextview=(TextView) findViewById(R.id.problem);
        timez=(TextView)findViewById(R.id.time);
        res=(TextView)findViewById(R.id.result);

        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        sumTextview.setVisibility(View.VISIBLE);
        timez.setVisibility(View.VISIBLE);
        rite.setVisibility(View.VISIBLE);
        wrang.setVisibility(View.VISIBLE);
        res.setVisibility(View.VISIBLE);


        button1.animate().alpha(1f).setDuration(300);
        button2.animate().alpha(1f).setDuration(300);
        button3.animate().alpha(1f).setDuration(300);
        button4.animate().alpha(1f).setDuration(300);
        sumTextview.animate().alpha(1f).setDuration(300);
        timez.animate().alpha(1f).setDuration(300);
        rite.animate().alpha(1f).setDuration(300);
        wrang.animate().alpha(1f).setDuration(300);
        playAgain.animate().translationYBy(1000f);



        new CountDownTimer(30300,1000){


            @Override
            public void onTick(long millisUntilFinished) {

                timez.setText("TIME:"+String.valueOf(millisUntilFinished/1000)+"s");

            }

            @Override
            public void onFinish(){


                highs.add(rt);

                System.out.print("Highest Score="+Collections.max(highs));
                Log.i("Highest Score",""+highs);




                timez.setText("TIME:0s");
                res.setText("YOUR SCORE = "+Integer.toString(rt)+"\n\t\t\t\t\t\t\tTIME OUT");
                res.animate().alpha(1f).setDuration(300);
                playAgain.animate().translationYBy(-1000f).setDuration(1000);


                playAgain=(Button)findViewById(R.id.playagain);
                playAgain.setVisibility(View.VISIBLE);

                button1.setEnabled(false);

                button2.setEnabled(false);

                button3.setEnabled(false);

                button4.setEnabled(false);





            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1=(Button)findViewById(R.id.an1);
        button2=(Button)findViewById(R.id.an2);
        button3=(Button)findViewById(R.id.an3);
        button4=(Button)findViewById(R.id.an4);

        sumTextview=(TextView) findViewById(R.id.problem);
        timez=(TextView)findViewById(R.id.time);

        newQuestion();



    }
}
