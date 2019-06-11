package com.example.scarnesdice;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int userOverallScore;
    private int userTurnScore;
    private int comOverallScore;
    private int comTurnScore;

    Button roll, hold, reset;
    TextView score;
    ImageView diceImage;
    int dice[] = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roll = (Button) findViewById(R.id.button);
        hold = (Button) findViewById(R.id.button2);
        reset = (Button) findViewById(R.id.button3);
        score = (TextView) findViewById(R.id.textView);
        diceImage = (ImageView) findViewById(R.id.imageView);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomDice = rollDice();
                randomDice++;
                if (randomDice == 1) {
                    score.setText("Your Score:" + userOverallScore + " Computer Score:" + comOverallScore);
                    userTurnScore = 0;
                    computerTurn();
                } else {
                    userTurnScore += randomDice;
                    score.setText("Your Score:" + userOverallScore + " Computer Score:" + comOverallScore + " Your Turn Score:" + userTurnScore);
                }

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("###########");
                userOverallScore = 0;
                userTurnScore = 0;
                comOverallScore = 0;
                comTurnScore = 0;
                score.setText("Your Score:" + userOverallScore + " Computer Score:" + comOverallScore);
            }
        });


        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("&&&&&&&&&&&&&&&&&7");
                userOverallScore += userTurnScore;
                userTurnScore = 0;
                if (userOverallScore > 100) {
                    score.setText("You Won!!");
                    return;
                }
                score.setText("Your Score:"+userOverallScore+" Computer Score:"+comOverallScore);
                computerTurn();
            }
        });

    }

    public int rollDice() {
        Random random = new Random();
        int randomDice = random.nextInt(6);
        diceImage.setImageResource(dice[randomDice]);
        return randomDice;

    }

    private void computerTurn() {
        roll.setEnabled(false);
        hold.setEnabled(false);
        reset.setEnabled(false);
        //while (true) {
            new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    int randomDice = rollDice();
                    randomDice++;
                    if (randomDice == 1) {
                        Toast.makeText(getApplicationContext(), "Computer rolled a:" + randomDice, Toast.LENGTH_SHORT).show();
                        comTurnScore = 0;
                        userTurn();
                        return;
                    } else {
                        comTurnScore += randomDice;
                        if (comOverallScore + comTurnScore > 100) {
                            score.setText("Computer Won!!");
                            return;
                        }
                        if (comTurnScore >= 20) {
                            userTurn();
                            return;
                        } else {
                            computerTurn();
                        }
                        Toast.makeText(getApplicationContext(), "Computer rolled a:" + randomDice, Toast.LENGTH_SHORT).show();
                        score.setText("Your Score:" + userOverallScore + " Computer Score:" + comOverallScore + " Computer Turn Score:" + comTurnScore);
                    }
                }
            }.start();
        }
   // }

    private  void userTurn() {
        comOverallScore+=comTurnScore;
        comTurnScore=0;
        score.setText("Your Score:" + userOverallScore + " Computer Score:" + comOverallScore);
        roll.setEnabled(true);
        hold.setEnabled(true);
        roll.setEnabled(true);
    }

}
