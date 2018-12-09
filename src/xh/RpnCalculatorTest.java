package xh;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by huangxiaohui on 09/12/2018.
 */
public class RpnCalculatorTest {

  private RpnCalculator calculator = new RpnCalculator();

  @Test
  public void testCalc_multiply() {
    String[] inputs = {"1", "2", "3", "*"};
    final Stack<String> result = calculator.calc(inputs);
    Assert.assertEquals(result.pop(), "6");
    Assert.assertEquals(result.pop(), "1");
  }

  @Test
  public void testCalc_divide() {
    String[] inputs = {"1", "6", "3", "/"};
    final Stack<String> result = calculator.calc(inputs);
    Assert.assertEquals(result.pop(), "2");
    Assert.assertEquals(result.pop(), "1");
  }

  // TODO: need to test other cases including: undo, clear, insucient parameters, etc...
}
