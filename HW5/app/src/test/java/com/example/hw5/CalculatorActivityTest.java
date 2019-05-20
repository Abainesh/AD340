package com.example.hw5;
import static com.google.common.truth.Truth.assertThat;
import android.content.Context;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import org.junit.Before;
import org.junit.Test;

//Mockito Test
public class CalculatorActivityTest {
    Calculator ourCalculator;

    @Mock
    Context mockContext;

    @Before
    public void setUp(){
        ourCalculator = new Calculator(4,4);

        MockitoAnnotations.initMocks((this));

        when(mockContext.getString(R.string.number_one))
                .thenReturn("1");
        when(mockContext.getString(R.string.number_two))
                .thenReturn("2");

    }
       @Test
    public void testCalculatorAdd(){
        Calculator calc = new Calculator(1,2);
        double actual = Calculator.add(1,2);
        assertThat(actual).isEqualTo(3.0);
    }
    @Test
    public void testCalculatorSubtract(){
        Calculator calc = new Calculator(4,2);
        double actual = Calculator.subtract(4,2);
        assertThat(actual).isEqualTo(2.0);
    }

    @Test
    public void testCalculatorMultiply(){
        Calculator calc = new Calculator(1,2);
        double actual = Calculator.multiply(1,2);
        assertThat(actual).isEqualTo(2.0);
    }

    @Test
    public void testCalculatorDivide() {
        Calculator calc = new Calculator(9, 3);
        double actual = Calculator.divide(9, 3);
        assertThat(actual).isEqualTo(3.0);
    }
}

