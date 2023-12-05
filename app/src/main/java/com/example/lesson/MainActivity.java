package com.example.lesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation leftToRight = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        Animation rightToLeft = AnimationUtils.loadAnimation(this, R.anim.right_to_left);

        Button converterButton = findViewById(R.id.ConverterButton);
        Button randomGeneratorButton = findViewById(R.id.RandomButton);
        Button SMSButton = findViewById(R.id.SMSButton);
        TextView textView1 = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);

        converterButton.startAnimation(leftToRight);
        randomGeneratorButton.startAnimation(rightToLeft);
        SMSButton.startAnimation(leftToRight);
        textView1.startAnimation(rightToLeft);
        textView2.startAnimation(leftToRight);

        converterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Convertor.class);
                startActivity(intent);
            }
        });

        randomGeneratorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RandomGenerator.class);
                startActivity(intent);
            }
        });

        SMSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SMS.class);
                startActivity(intent);
            }
        });
    }
}
