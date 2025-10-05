
package com.example.bmiapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Result extends AppCompatActivity {

    TextView txtresult;
    SeekBar seekBarBMI;
    TextView txtBMICategory, txtBMIscore, txtBMIchartdata;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        getWindow().setStatusBarColor(ContextCompat.getColor(Result.this, R.color.black));

        txtresult = findViewById(R.id.txtresult);
        txtBMIscore = findViewById(R.id.txtBMIscore);
        txtBMICategory = findViewById(R.id.txtBMICategory);
        txtBMIchartdata = findViewById(R.id.txtBMIchartdata);

        Intent intent = getIntent();
        float bmi = intent.getFloatExtra("bmi",0.0f);
        txtresult.setText("Your BMI is: " + String.format("%.2f", bmi)+ " kg/m2");

        int bmiPercentage = (int) ((bmi / 40.0) * 100);
        txtBMIscore.setText(String.valueOf(bmiPercentage) + "%");

        seekBarBMI.setProgress(bmiPercentage);


        String category;
        if (bmi < 18.5) {
            category = "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            category = "Healthy";
        } else if (bmi >= 25 && bmi < 29.9) {
            category = "Overweight";
        } else if (bmi >= 30 && bmi < 34.9) {
            category = "Obese";
        }
        else {
            category = "Severly Obese";
        }

        txtBMICategory.setText(category);

    }
}
