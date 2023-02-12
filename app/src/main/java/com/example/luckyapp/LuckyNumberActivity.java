package com.example.luckyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LuckyNumberActivity extends AppCompatActivity {

    TextView welcomeTxt, luckyNumbertxt;
    Button shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_number);

        welcomeTxt = findViewById(R.id.textView2);
        luckyNumbertxt = findViewById(R.id.luckyNumberText);
        shareBtn = findViewById(R.id.shareNumberBtn);

        //Username
        Intent i = getIntent();
        String userName = i.getStringExtra("name");

        //Random Number Generated
        int random_Num = generateRandomNumber();

        luckyNumbertxt.setText(""+random_Num);

//        Toast.makeText(this,"Username: "+userName,Toast.LENGTH_LONG).show();

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName,random_Num);
            }
        });

    }


    public int generateRandomNumber() {

        Random random = new Random();
        int upper_limit = 1000;

        int randomNumberGenerated = random.nextInt(1000);
        return randomNumberGenerated;
    }


    public void shareData(String username,int randomNum ) {

        //Implicit Intents
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        String number = String.valueOf(randomNum);

        i.putExtra(Intent.EXTRA_SUBJECT, username + " got lucky today!");
        i.putExtra(Intent.EXTRA_TEXT, "His lucky number "+number);

        startActivity(Intent.createChooser(i,"Choose a platform"));


    }
}