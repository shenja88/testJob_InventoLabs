package test_two;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class FactorialUtil {

    public double proceedFactorial(int i, TypeProceed type) {
        System.out.println(String.format(
                "Calculate operation (un = (1 / n!) * (1! + 2! + 3! + ... + n!)) is started, number - %s, factorial type - %s.", i, type));
        return switch (type) {
                case RECURSION -> calculateFactorialR(i);
                case LOOPS -> calculateFactorialL(i);
        };
    }

    private double calculateFactorialR (int i) {
        double partOne = (double) 1 / getFactorialR(i);
        AtomicLong twoPart = new AtomicLong();
        IntStream.range(1, i + 1).forEach(n -> {
            int factorial = getFactorialR(n);
            twoPart.addAndGet(factorial);
        });
        double result = partOne * twoPart.get();
        System.out.println(String.format("Result operation: %s.", result));
        return result;
    }

    private int getFactorialR (int i) {
        if (i <= 1) {
            return 1;
        }
        return i * getFactorialR(i - 1);
    }

    private double calculateFactorialL (int i) {
        double partOne = (double) 1 / getFactorialL(i);
        AtomicLong twoPart = new AtomicLong();
        IntStream.range(1, i + 1).forEach(n -> {
            int factorial = getFactorialL(n);
            twoPart.addAndGet(factorial);
        });
        double result = partOne * twoPart.get();
        System.out.println(String.format("Result operation: %s.", result));
        return result;
    }

    private int getFactorialL (int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public boolean checkMoveTo0(List<Double> resultList) {
        AtomicBoolean check = new AtomicBoolean(false);
        resultList.forEach(d -> {
            int index = resultList.indexOf(d);
            if (index != 0) {
                check.set(d < resultList.get(index - 1));
            }
        });
        return check.get();
    }
}
