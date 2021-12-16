package com.example.bmicalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    android.widget.Button calculatebmi;
    TextView mCurrentHeight;
    TextView mCurrentWeight,mCurrentAge;
    ImageView incrementWeight,decrementWeight;
    ImageView incrementAge,decrementAge;
    SeekBar heightSeekbar;
    RelativeLayout mMale,mFemale;
    int weight = 50;
    int age = 18;
    int currentProgress;
    String mintProgress="170";
    String typeOfUser = "0";
    String weight2 = "50";
    String age2 = "18";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Typecasting the variables

        calculatebmi = findViewById(R.id.calculateBMI);

        mCurrentHeight = findViewById(R.id.currentHeight);
        mCurrentAge = findViewById(R.id.currentAge);
        mCurrentWeight = findViewById(R.id.currentWeight);

        incrementAge = findViewById(R.id.Increment_age);
        decrementAge = findViewById(R.id.decrement_age);

        incrementWeight = findViewById(R.id.Increment_weight);
        decrementWeight = findViewById(R.id.decrement_weight);

        heightSeekbar = findViewById(R.id.Seekbar_Height);

        mMale = findViewById(R.id.gender_male);
        mFemale = findViewById(R.id.gender_female);


        //Seekbar Implementation

        heightSeekbar.setMax(300);
        heightSeekbar.setProgress(170);
        heightSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentProgress = progress;
                mintProgress = String.valueOf(currentProgress);
                mCurrentHeight.setText(mintProgress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //Increment Age and Weight

        incrementAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = age + 1;
                age2 = String.valueOf(age);
                mCurrentAge.setText(age2);

            }
        });

        incrementWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight = weight + 1;
                weight2 = String.valueOf(weight);
                mCurrentWeight.setText(weight2);

            }
        });

        //Decrement Age and Weight

        decrementAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = age - 1;
                age2 = String.valueOf(age);
                mCurrentAge.setText(age2);

            }
        });

        decrementWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight = weight - 1;
                weight2 = String.valueOf(weight);
                mCurrentWeight.setText(weight2);
            }
        });

        //Calculate Button Logic

        calculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeOfUser == "0") {
                    Toast.makeText(getApplicationContext(), "Please select gender", Toast.LENGTH_SHORT).show();
                } else if (mintProgress.equals("0")) {
                    Toast.makeText(getApplicationContext(), "Please select Height", Toast.LENGTH_SHORT).show();
                } else if (age == 0 || age < 0) {
                    Toast.makeText(getApplicationContext(), "Invalid Age", Toast.LENGTH_SHORT).show();
                } else if (weight == 0 || weight < 0) {
                    Toast.makeText(getApplicationContext(), "Invalid Weight", Toast.LENGTH_SHORT).show();
                } else{

                    Intent intent = new Intent(MainActivity.this,bmiactivity.class);
                    intent.putExtra("gender",typeOfUser);
                    intent.putExtra("age",age2);
                    intent.putExtra("weight",weight2);
                    intent.putExtra("height",mintProgress);

                    startActivity(intent);
                }

            }

        });


        mMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocused));
                mFemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocused));
                typeOfUser = "Male";

            }
        });

        mFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocused));
                mMale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocused));
                typeOfUser = "Female";

            }
        });



    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}