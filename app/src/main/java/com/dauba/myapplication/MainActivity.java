package com.dauba.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String currentOperation = "";
    private boolean isSecondNumber = false;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Number buttons
        View.OnClickListener numberListener = view -> {
            Button button = (Button) view;
            String buttonText = button.getText().toString();
            if (isSecondNumber) {
                display.append(buttonText);  // Append the second number after the operation
            } else {
                if (display.getText().toString().equals("0")) {
                    display.setText(buttonText);  // Replace the "0" if it's the first input
                } else {
                    display.append(buttonText);  // Append digits to form the first number
                }
            }
        };

        findViewById(R.id.button_0).setOnClickListener(numberListener);
        findViewById(R.id.button_1).setOnClickListener(numberListener);
        findViewById(R.id.button_2).setOnClickListener(numberListener);
        findViewById(R.id.button_3).setOnClickListener(numberListener);
        findViewById(R.id.button_4).setOnClickListener(numberListener);
        findViewById(R.id.button_5).setOnClickListener(numberListener);
        findViewById(R.id.button_6).setOnClickListener(numberListener);
        findViewById(R.id.button_7).setOnClickListener(numberListener);
        findViewById(R.id.button_8).setOnClickListener(numberListener);
        findViewById(R.id.button_9).setOnClickListener(numberListener);

        // Decimal button
        findViewById(R.id.button_decimal).setOnClickListener(view -> {
            if (!display.getText().toString().contains(".")) {
                display.append(".");
            }
        });

        // Clear button
        findViewById(R.id.button_clear).setOnClickListener(view -> {
            display.setText("0");
            firstNumber = 0;
            secondNumber = 0;
            currentOperation = "";
            isSecondNumber = false;
        });

        // Operation buttons
        View.OnClickListener operationListener = view -> {
            Button button = (Button) view;
            currentOperation = button.getText().toString();
            firstNumber = Double.parseDouble(display.getText().toString());
            isSecondNumber = true;


            // Append the operation to the display so the user can see it
            display.append(" " + currentOperation + " ");
        };

        findViewById(R.id.button_add).setOnClickListener(operationListener);
        findViewById(R.id.button_subtract).setOnClickListener(operationListener);
        findViewById(R.id.button_multiply).setOnClickListener(operationListener);
        findViewById(R.id.button_divide).setOnClickListener(operationListener);

        // Equals button
        findViewById(R.id.button_equals).setOnClickListener(view -> {
            // Split the display text to extract the second number
            String[] parts = display.getText().toString().split(" ");

            // Ensure we have both the first number, operation, and second number
            if (parts.length < 3) {
                return;  // Prevents crashing if input is incomplete
            }

            try {
                secondNumber = Double.parseDouble(parts[2]);
            } catch (NumberFormatException e) {
                display.setText("Error");
                return;
            }

            double result = 0;

            switch (currentOperation) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "×":
                    result = firstNumber * secondNumber;
                    break;
                case "÷":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            // Display the result
            display.setText(decimalFormat.format(result));
            isSecondNumber = false;
        });
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("displayText", display.getText().toString());
        outState.putDouble("firstNumber", firstNumber);
        outState.putDouble("secondNumber", secondNumber);
        outState.putString("currentOperation", currentOperation);
        outState.putBoolean("isSecondNumber", isSecondNumber);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        display.setText(savedInstanceState.getString("displayText"));
        firstNumber = savedInstanceState.getDouble("firstNumber");
        secondNumber = savedInstanceState.getDouble("secondNumber");
        currentOperation = savedInstanceState.getString("currentOperation");
        isSecondNumber = savedInstanceState.getBoolean("isSecondNumber");
    }
}