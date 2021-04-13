package com.ex.tests;

import com.ex.Calculator;
import com.ex.SimpleCalculator;
import org.junit.*;

public class SimpleCalculatorTests {
    private Calculator calc;

    @Before
    public void beforeEachTest() {
        System.out.println("Before test");
        calc = new SimpleCalculator();
    }

    @After
    public void afterEachTest() {
        System.out.println("After test");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After class");
    }

    @Test
    public void shouldAddOperands() {
        int sum = calc.add(2, 2);
        int expected = 4;
        Assert.assertEquals("expected sum didn't match actual sum", expected, sum);
    }

    @Test
    public void shouldSubtractOperands() {
        int total = calc.sub(5, 3);
        int expected = 2;
        Assert.assertEquals("expected total didn't match", expected, total);
    }

    @Test(expected = ArithmeticException.class)
    public void shouldThrowArithmeticExceptionWhenDivideByZero() {
        int quot = calc.div(2, 0);
    }
}
