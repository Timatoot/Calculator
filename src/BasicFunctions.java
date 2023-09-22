import java.util.*;
import java.math.BigDecimal;
import java.util.function.BiFunction;

public class BasicFunctions {
    static String[] ops = { "*", "-", "+" };
    static List<BiFunction<BigDecimal, BigDecimal, BigDecimal>> operations = Arrays.asList(BigDecimal::multiply, BigDecimal::subtract, BigDecimal::add);

    static double eval(String input) {
        input = input.replaceAll("=", "");
        input = checkBrackets(input);
        double result = 0;
        List<List<String>> algoResult = Algorithm.trigAndOtherFunctions(input);
        List<String> operator = algoResult.get(0);
        List<String> number = algoResult.get(1);
        for (int k = 0; k < ops.length; k++) {
            for (int i = 0; i < operator.size(); i++) {
                for (int j = 0; j < number.size() - 1; j++) {
                    BigDecimal bigDNum1 = new BigDecimal(number.get(i));
                    BigDecimal bigDNum2 = new BigDecimal(number.get(i + 1));
                    if (ops[k].equals(operator.get(i))) {
                        result = operations.get(k).apply(bigDNum1, bigDNum2).doubleValue();
                        number.remove(i + 1);
                        number.set(i, String.valueOf(result));
                        operator.remove(i);
                        i--;
                    } else if (operator.get(i).equals("/")) {
                        result = bigDNum1.divide(bigDNum2, 10, BigDecimal.ROUND_HALF_UP).doubleValue();
                        number.remove(i + 1);
                        number.set(i, String.valueOf(result));
                        operator.remove(i);
                    } else if (operator.get(i).equals("^")) {
                        result = Math.pow(Double.parseDouble(number.get(i)), Double.parseDouble(number.get(i + 1)));
                        number.remove(i + 1);
                        number.set(i, String.valueOf(result));
                        operator.remove(i);
                    }
                }
            }
        }
        if (operator.size() == 0) result = Double.parseDouble(number.get(0));
        return result;
    }

    static String checkBrackets(String input) {
        if (input.contains("(")) {
            int start = input.lastIndexOf("(");
            int end = input.indexOf(")", start);
            String inBrackets = input.substring(start + 1, end);
            double result = eval(inBrackets);
            input = input.substring(0, start) + result + input.substring(end + 1);
        }
        return input;
    }
}
