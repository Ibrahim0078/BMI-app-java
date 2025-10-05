package com.example.bmiapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtweight, edtheight;
    Button btnCalculate;
    TextView txtresult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.black));

        edtweight = findViewById(R.id.edtweight);
        edtheight = findViewById(R.id.edtheight);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtresult = findViewById(R.id.txtresult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float bmi = calculateBMI();
                if (bmi == 0) {
                    Toast.makeText(MainActivity.this, "Please enter all fields correctly", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i1 = new Intent(MainActivity.this, Result.class);
                    i1.putExtra("bmi", bmi);
                    startActivity(i1);
                }
            }
        });
    }

    private float calculateBMI() {
        String weightStr = edtweight.getText().toString();
        String heightStr = edtheight.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            return 0;
        }

        float weight = Float.parseFloat(weightStr);
        int heightFt = 0;
        int heightIn = 0;

        try {
            if (heightStr.contains(".")) {
                String[] heightParts = heightStr.split("\\.");
                heightFt = Integer.parseInt(heightParts[0]);
                heightIn = Integer.parseInt(heightParts[1]);
            } else {
                heightFt = Integer.parseInt(heightStr);
            }
        } catch (NumberFormatException e) {
            return 0;
        }

        int totalHeightIn = heightFt * 12 + heightIn;
        float heightInMeters = totalHeightIn * 0.0254f;

        return weight / (heightInMeters * heightInMeters);
    }

}
