package com.example.lesson;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RandomGenerator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_generator);
    }

    @SuppressLint("SetTextI18n")
    public void generateProgressBars(View view) {
        EditText editTextAdet = findViewById(R.id.editTextAdet);
        EditText editTextMin = findViewById(R.id.editTextMin);
        EditText editTextMax = findViewById(R.id.editTextMax);

        int adet = Integer.parseInt(editTextAdet.getText().toString());
        int min = Integer.parseInt(editTextMin.getText().toString());
        int max = Integer.parseInt(editTextMax.getText().toString());

        if (min >= max) {
            Toast.makeText(this, "Error: Minimum value must be lower than maximum value.", Toast.LENGTH_SHORT).show();
            return;
        }

        LinearLayout containerLayout = findViewById(R.id.containerLayout);
        containerLayout.removeAllViews();

        int[] minValues = new int[adet];
        int[] maxValues = new int[adet];

        for (int i = 0; i < adet; i++) {
            int randomMin = new Random().nextInt(max - min) + min;
            int randomMax = new Random().nextInt(max - min) + min;

            while (randomMin >= randomMax) {
                randomMin = new Random().nextInt(max - min) + min;
                randomMax = new Random().nextInt(max - min) + min;
            }

            int ortaDeger = Math.min(randomMin, randomMax) + new Random().nextInt(Math.abs(randomMax - randomMin) + 1);

            int ilerlemeYuzdesi = (ortaDeger - randomMin) * 100 / (randomMax - randomMin);

            ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
            progressBar.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            progressBar.setMax(100);
            progressBar.setProgress(ilerlemeYuzdesi);

            TextView textView = new TextView(this);
            textView.setText("Min:" + randomMin + "    Value: " + ortaDeger + "    Percentage: %" + ilerlemeYuzdesi + "    Max: " + randomMax);

            textView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams progressBarParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            progressBarParams.bottomMargin = 16;

            progressBar.setLayoutParams(progressBarParams);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.gravity = Gravity.CENTER;
            layoutParams.bottomMargin = 100;

            textView.setLayoutParams(layoutParams);

            containerLayout.addView(progressBar);
            containerLayout.addView(textView);
        }
    }
}

