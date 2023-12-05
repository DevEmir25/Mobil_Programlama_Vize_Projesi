package com.example.lesson;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SMS extends AppCompatActivity {

    private EditText phoneNumber, message;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        phoneNumber = findViewById(R.id.phoneNumber);
        message = findViewById(R.id.descriptionText);
        sendButton = findViewById(R.id.send);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = phoneNumber.getText().toString().replaceAll("[^0-9]", "");
                String msg = message.getText().toString();

                if (checkSelfPermission("android.permission.SEND_SMS") == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(getApplicationContext(), SMS.class);
                    PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(number, null, msg, pi, null);

                    Toast.makeText(SMS.this, "Message Sent", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SMS.this, "Permission denied: SEND_SMS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
