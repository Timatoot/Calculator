import java.util.function.Function;

public class TestEmuns {
    public static void main(String[] args) {

        int result = TestFunctions.ADD.call(10, 20, 50);
        System.out.println("ADD: " + result);
    }

    public enum TestFunctions {
        ADD(a -> {
            int sum = 0;
            for (int i : a) {
                sum = Math.addExact(sum, i);
            }
            return sum;
        }),
        SUBTRACT(a -> Math.subtractExact(a[0], a[1])),
        MULTIPLY(a -> Math.multiplyExact(a[0], a[1])),
        DIVIDE(a -> Math.floorDiv(a[0], a[1]));

        Function<Integer[], Integer> function;

        private TestFunctions(Function<Integer[], Integer> function) {
            this.function = function;
        }

        public Integer call(Integer... input) {
            return this.function.apply(input);
        }
    }
}

