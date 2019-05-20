package com.example.hw5;
import android.view.View;
import android.content.Context;
//Calculator Activity Model
public class Calculator {
    private double number1;
    private double number2;

    public double getNumber1() {
        return number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }
    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public static double add(double n1, double n2){

        return n1+n2;
    }
    public static double subtract(double n1, double n2){

        return n1-n2;
    }public static double multiply(double n1,  double n2){

        return n1*n2;
    }public static double divide(double n1,  double n2){

        return n1/n2;
    }
    public Calculator(double number1, double number2){
        this.number1 = number1;
        this.number2 = number2;

    }
    //Adding a simple Mockito Mock to test
    public Calculator(Context context){
        this.number1 = Double.parseDouble(context.getString(R.string.number_one));
        this.number2 = Double.parseDouble(context.getString(R.string.number_two));

    }

}
