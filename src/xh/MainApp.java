package xh;

import com.sun.deploy.util.StringUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Created by huangxiaohui on 09/12/2018.
 */
public class MainApp {

  public static void main(String[] args) {
    // get input from command line
    final Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter RPN numbers and operators: (end by pressing enter key)");
    System.out.println("(Press Command+D to exit the program):");

    RpnCalculator calculator = new RpnCalculator();
    while (scanner.hasNextLine()) {
      final String tokenString = scanner.nextLine();
      String[] inputs = StringUtils.splitString(tokenString, " ");
      List<String> result = calculator.calc(inputs);
      System.out.println("stack: " + result.toString());
    }

  }
}
