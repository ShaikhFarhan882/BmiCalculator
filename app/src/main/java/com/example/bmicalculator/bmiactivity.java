package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class bmiactivity extends AppCompatActivity {

    android.widget.Button previousPage;

    TextView bmiDisplay,bmiCategory,showGender;
    Intent intent;
    ImageView imageView;

    String mbmi;
    float resultBMI;

    String height;
    String weight;
    float fweight,fheight;

    RelativeLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);
        previousPage = findViewById(R.id.previousPage);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color =\"white\"></font>"));
        getSupportActionBar().setTitle("RESULT");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1E1D1D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        intent = getIntent();



        bmiDisplay = findViewById(R.id.Bmi_value);
        bmiCategory = findViewById(R.id.Category);
        showGender = findViewById(R.id.gender_show);
        imageView = (ImageView) findViewById(R.id.checked_logo);
        background = findViewById(R.id.contentlayout);

        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");

        fheight = Float.parseFloat(height);
        fweight = Float.parseFloat(weight);

        fheight = fheight/100;

        resultBMI = fweight / (fheight * fheight);

        mbmi = Float.toString(resultBMI);

        if (resultBMI < 16){
            bmiCategory.setText("Severe Thinness");
            background.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.cancel);
        }

        else if (resultBMI < 16.9 && resultBMI > 16 ){
            bmiCategory.setText("Moderate Thinness");
            background.setBackgroundColor(Color.YELLOW);
            imageView.setImageResource(R.drawable.warning);
        }

        else if (resultBMI < 18.4 && resultBMI > 17 ){
            bmiCategory.setText("Mild Thinness");
            background.setBackgroundColor(Color.YELLOW);
            imageView.setImageResource(R.drawable.warning);
        }

        else if (resultBMI < 25 && resultBMI > 18.4 ){
            bmiCategory.setText("Normal");
            background.setBackgroundColor(Color.GREEN);
            imageView.setImageResource(R.drawable.checked);
        }

        else if (resultBMI < 29.4 && resultBMI > 25 ){
            bmiCategory.setText("Overweight");
            background.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.cancel);
        }

        else {
            bmiCategory.setText("Dul Dul PAPI");
            background.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.cancel);

        }

        showGender.setText(intent.getStringExtra("gender"));
        bmiDisplay.setText(mbmi);


        previousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bmiactivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}