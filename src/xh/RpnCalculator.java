package xh;


import java.math.BigDecimal;
import java.util.Stack;

/**
 * Created by huangxiaohui on 09/12/2018.
 */
public class RpnCalculator {
  private Stack<String> currentNumbers = new Stack<>();
  private Stack<Stack<String>> snapshots = new Stack<>();
  private static int MAX_STACK_SIZE = 100;

  private String errorMsgTemplate = "operator %s (position: %d): insufficient parameters";

  // TODO: need to consider decimal or Fraction
  private boolean isNumeric(String str) {
    for (int i = str.length() - 1; i>=0; i--) {
      if (!Character.isDigit(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public Stack<String> calc(String[] inputs) {
    int currentIdx = 0;
    for (String ele : inputs) {
      currentIdx++;

      if (ele.toLowerCase().equals("undo")) {
        if (snapshots.size() == 0) {
          continue;
        }
        currentNumbers = snapshots.pop();
        continue;
      }
      // add a snapshot for before change
      snapshots.push((Stack<String>)currentNumbers.clone());
      if (snapshots.size() > MAX_STACK_SIZE) {
        // TODO: implement a stack with fixed size in order to prevent OOM
        System.out.println("warning: the stack size is too big, need to clean");
      }

      if (isNumeric(ele)) {
        currentNumbers.push(ele);
        continue;
      }
      String secondNum;
      String firstNum;
      BigDecimal result;
      // supported operators: "+", "-", "*", "/", "undo", "clear", "sqrt"
      switch (ele.toLowerCase()) {
        case "+" :
          if (currentNumbers.size() < 2) {
            System.err.println(String.format(errorMsgTemplate, ele, currentIdx));
            return currentNumbers;
          }
          secondNum = currentNumbers.pop();
          firstNum = currentNumbers.pop();
          result = new BigDecimal(firstNum).add(new BigDecimal(secondNum));
          currentNumbers.push(result.toPlainString());
          break;

        case "-":
          if (currentNumbers.size() < 2) {
            System.err.println(String.format(errorMsgTemplate, ele, currentIdx));
            return currentNumbers;
          }
          secondNum = currentNumbers.pop();
          firstNum = currentNumbers.pop();
          result = new BigDecimal(firstNum).subtract(new BigDecimal(secondNum));
          currentNumbers.push(result.toPlainString());
          break;

        case "*":
          if (currentNumbers.size() < 2) {
            System.err.println(String.format(errorMsgTemplate, ele, currentIdx));
            return currentNumbers;
          }
          secondNum = currentNumbers.pop();
          firstNum = currentNumbers.pop();
          result = new BigDecimal(firstNum).multiply(new BigDecimal(secondNum));
          currentNumbers.push(result.toPlainString());
          break;

        case "/":
          if (currentNumbers.size() < 2) {
            System.err.println(String.format(errorMsgTemplate, ele, currentIdx));
            return currentNumbers;
          }
          secondNum = currentNumbers.pop();
          firstNum = currentNumbers.pop();
          result = new BigDecimal(firstNum).divide(new BigDecimal(secondNum));
          currentNumbers.push(result.toPlainString());
          break;

        case "clear":
          currentNumbers.clear();
          break;

        case "sqrt":
          if (currentNumbers.size() < 1) {
            System.err.println(String.format(errorMsgTemplate, ele, currentIdx));
            return currentNumbers;
          }
          BigDecimal num = new BigDecimal(currentNumbers.pop());
          Double res = Math.sqrt(num.doubleValue());
          currentNumbers.push(res.toString());
          break;

        default:
            System.err.println("unsupported operator: " + ele + " !");
      }

    }

    return currentNumbers;
  }

}
