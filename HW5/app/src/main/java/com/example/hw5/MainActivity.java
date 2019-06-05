package com.example.hw5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// As shown in https://www.androidauthority.com/build-a-calculator-app-721910/
//https://www.bing.com/videos/search?q=create+simple+commission+app+in+android+studio&&view=detail&mid=DD9A377A8D411A1DC52ADD9A377A8D411A1DC52A&&FORM=VRDGAR
public class MainActivity extends AppCompatActivity {
    EditText number1, number2;
    TextView result;
    Button add, subtract, multiply, divide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Type Casting by converting result into an EditText
        number1 = (EditText) findViewById(R.id.num1);
        number2 = (EditText) findViewById(R.id.num2);

        result = (TextView) findViewById(R.id.result);

        add = (Button) findViewById(R.id.buttonAdd);
        subtract = (Button) findViewById(R.id.buttonSubtract);
        multiply = (Button) findViewById(R.id.buttonMultiply);
        divide = (Button) findViewById(R.id.buttonDivide);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Check to make sure value provided is not NULL-Condition
                if (!number1.getText().toString().isEmpty() && !number2.getText().toString().isEmpty()) {
                    double n1 = Double.parseDouble(number1.getText().toString());
                    double n2 = Double.parseDouble(number2.getText().toString());
                    double res = Calculator.add(n1,n2);

                    result.setText(String.valueOf(res));
                }
                else {
                    //Create toast message to get proper value from user-error checking
                    Toast.makeText(view.getContext(),"Please enter numbers properly", Toast.LENGTH_SHORT).show();
                }
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Check to make sure value provided is not NULL-Condition
                if (!number1.getText().toString().isEmpty() && !number2.getText().toString().isEmpty()) {
                    double n1 = Double.parseDouble(number1.getText().toString());
                    double n2 = Double.parseDouble(number2.getText().toString());
                    double res = Calculator.subtract(n1,n2);

                    result.setText(String.valueOf(res));
                } else {
                    //Create toast message to get proper value from user-error checking
                    Toast.makeText(view.getContext(), "Please enter numbers properly", Toast.LENGTH_SHORT).show();
            }
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Check to make sure value provided is not NULL-Condition
                if (!number1.getText().toString().isEmpty() && !number2.getText().toString().isEmpty()) {
                    double n1 = Double.parseDouble(number1.getText().toString());
                    double n2 = Double.parseDouble(number2.getText().toString());
                    double res = Calculator.multiply(n1,n2);

                    result.setText(String.valueOf(res));
                }
                else {
                    //Create toast message to get proper value from user-error checking
                    Toast.makeText(view.getContext(), "Please enter numbers properly", Toast.LENGTH_SHORT).show();
                }
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Check to make sure value provided is not NULL-Condition
                if (!number1.getText().toString().isEmpty() && !number2.getText().toString().isEmpty()) {
                    double n1 = Double.parseDouble(number1.getText().toString());
                    double n2 = Double.parseDouble(number2.getText().toString());
                    double res = Calculator.divide(n1,n2);
                    result.setText(String.valueOf(res));
                }
                else {
                    //Create toast message to get proper value from user-error checking
                    Toast.makeText(view.getContext(), "Please enter numbers properly", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}