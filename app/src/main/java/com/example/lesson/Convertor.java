package com.example.lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
public class Convertor extends AppCompatActivity {

    private EditText decimalInput;
    private Spinner baseSpinner;
    private TextView resultValue ;


    private EditText megabyteInput;
    private Spinner byteConversionSpinner;
    private TextView byteResultValue;


    private EditText celsiusInput;
    private RadioGroup conversionRadioGroup;
    private RadioButton fahrenheitRadioButton;
    private RadioButton kelvinRadioButton;
    private TextView temperatureResultValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor);

        decimalInput = findViewById(R.id.decimalInputText);
        baseSpinner = findViewById(R.id.decimalSpinner);
        resultValue = findViewById(R.id.resultValue);

        megabyteInput = findViewById(R.id.megabyteInput);
        byteConversionSpinner = findViewById(R.id.byteConversionSpinner);
        byteResultValue = findViewById(R.id.byteResultValue);

        celsiusInput = findViewById(R.id.celsiusInput);
        conversionRadioGroup = findViewById(R.id.conversionRadioGroup);
        fahrenheitRadioButton = findViewById(R.id.fahrenheitRadioButton);
        kelvinRadioButton = findViewById(R.id.kelvinRadioButton);
        temperatureResultValue = findViewById(R.id.temperatureResultValue);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.base_options, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        baseSpinner.setAdapter(adapter);


        decimalInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                convertAndDisplayResult();
            }
        });


        baseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                convertAndDisplayResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });


        megabyteInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                convertAndDisplayByteResult();
            }
        });

        byteConversionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                convertAndDisplayByteResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        celsiusInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                convertAndDisplayTemperatureResult();
            }
        });

        conversionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                convertAndDisplayTemperatureResult();
            }
        });
    }
    private void convertAndDisplayResult() {
        String input = decimalInput.getText().toString();
        if (input.isEmpty()) {
            resultValue.setText("");
            return;
        }

        int decimalNumber = Integer.parseInt(input);

        String selectedBase = baseSpinner.getSelectedItem().toString();
        int base;

        switch (selectedBase) {
            case "Binary":
                base = 2;
                break;
            case "Octal":
                base = 8;
                break;
            case "Hexadecimal":
                base = 16;
                break;
            default:
                base = 10;
        }

        String result = Integer.toString(decimalNumber, base);
        resultValue.setText(result);
    }


    private void convertAndDisplayByteResult() {
        String input = megabyteInput.getText().toString();
        if (input.isEmpty()) {
            byteResultValue.setText("");
            return;
        }

        double megabytes = Double.parseDouble(input);

        String selectedConversion = byteConversionSpinner.getSelectedItem().toString();
        double result;

        switch (selectedConversion) {
            case "Kilobyte":
                result = megabytes * 1024;
                break;
            case "Byte":
                result = megabytes * 1024 * 1024;
                break;
            case "Kibibyte":
                result = megabytes * 8 * 1024 * 1024;
                break;
            case "Bit":
                result = megabytes * 8 * 1024 * 1024 * 1024;
                break;
            default:
                result = megabytes;
        }

        byteResultValue.setText(result + " ");
    }


    private void convertAndDisplayTemperatureResult() {
        String input = celsiusInput.getText().toString();
        if (input.isEmpty()) {
            temperatureResultValue.setText(" ");
            return;
        }

        double celsius = Double.parseDouble(input);
        double result;

        if (fahrenheitRadioButton.isChecked()) {
            // Celsius to Fahrenheit conversion
            result = celsius * 9 / 5 + 32;
            temperatureResultValue.setText(result + " °F");
        } else if (kelvinRadioButton.isChecked()) {
            // Celsius to Kelvin conversion
            result = celsius + 273.15;
            temperatureResultValue.setText(result + " K");
        } else {
            temperatureResultValue.setText("Select a conversion option first.");
        }
    }
}