import java.util.function.DoubleUnaryOperator;
import java.util.*;

public class Algorithm {
    static Map<String, String> operatorMap = new HashMap<>();
    static {
        String[][] operatorAbbs = {{ "s", "sin" }, { "c", "cos" }, { "t", "tan" }, { "a", "asin" }, { "o", "acos" }, { "n", "atan" }, { "l", "log" }, { "r", "Sqrt" }, { "v", "abs" }, { "b", "Cbrt" }};
        for (String[] abb : operatorAbbs) operatorMap.put(abb[0], abb[1]);
    }
    static String[] operators = { "+", "-", "*", "/", "sin", "cos", "tan", "asin", "acos", "atan", "log", "Sqrt", "^", "abs", "Cbrt" };
    static DoubleUnaryOperator[] functions = { Math::sin, Math::cos, Math::tan, Math::asin, Math::acos, Math::atan, Math::log10, Math::sqrt, Math::abs, Math::cbrt};
    static String[] trig = { "sin", "cos", "tan", "asin", "acos", "atan", "log", "sqrt", "abs", "cbrt" };
    
    public static List<ArrayList<String>> algorithm(String input) {
        ArrayList<String> operator = new ArrayList<String>();
        ArrayList<String> number = new ArrayList<String>();
        String currentNumber = "";
        String currentOperator = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '-' && (i == 0 || !Character.isDigit(input.charAt(i - 1)))) currentNumber += c;
            else if (Character.isDigit(c) || c == '.') currentNumber += c;
            else if (c == 'p') currentNumber += Math.PI;
            else if (c == 'e') currentNumber += Math.E;
            else {
                if (!currentNumber.isEmpty()) {
                    number.add(currentNumber);
                    currentNumber = "";
                }
                currentOperator += c;
                if (Arrays.asList(operators).contains(currentOperator)) {
                    operator.add(operatorMap.getOrDefault(currentOperator, currentOperator));
                    currentOperator = "";
                }
            }
        }
        if (!currentNumber.isEmpty()) number.add(currentNumber);
        if (!currentOperator.isEmpty()) operator.add(operatorMap.getOrDefault(currentOperator, currentOperator));
        if (operator.contains("i")) operator.remove("i");
        return Arrays.asList(operator, number);
    }

    static List<List<String>> trigAndOtherFunctions(String input) {
        List<ArrayList<String>> algoResult = Algorithm.algorithm(input);
        List<String> operator = algoResult.get(0);
        List<String> number = algoResult.get(1);
            for (int j = 0; j < operator.size(); j++) {
                for (int i = 0; i < trig.length; i++) {
                    if (operator.get(j).equalsIgnoreCase(trig[i])) {
                        number.set(j, String.valueOf(functions[i].applyAsDouble(Double.parseDouble(number.get(j)))));
                        operator.remove(j);
                        if (j >= 1 && operator.size() != 0) j--;
                        else if (operator.size() == 0) break;
                    }
                }
            }
        return Arrays.asList(operator, number);
    }
}