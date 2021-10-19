package cs.lab1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    Calculator calculator;

    @Before
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void testChange() {
        String[][] tests = new String[][]{
                new String[]{"1+2*3", "1 2 3 *+"},
                new String[]{"1*2+3", "1 2 *3 +"},
        };
        for (int i = 0; i < tests.length; i++) {
            Assert.assertEquals(tests[i][1], calculator.change(tests[i][0]));
        }
    }

    @Test
    public void testCalculate() {
        String[][] tests = new String[][]{
                new String[]{"1+2*3", "7"},
                new String[]{"1*2+3", "5"},
        };
        for (int i = 0; i < tests.length; i++) {
            Assert.assertEquals(Integer.parseInt(tests[i][1]), calculator.calculate(calculator.change(tests[i][0])));
        }
    }
}