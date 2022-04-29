package test_two;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class App {

    public static void main(String[] args) {
        FactorialUtil util = new FactorialUtil();
        List<Double> resultList = new ArrayList<>();
        IntStream.range(1, 10).forEach(i -> {
            System.out.println("================================");
            for (TypeProceed type : TypeProceed.values()) {
                double result = util.proceedFactorial(i, type);
                resultList.add(result);            }
        });

        //expected -> true
        System.out.println("Function (un = (1 / n!) * (1! + 2! + 3! + ... + n!) moving to 0 -> " + util.checkMoveTo0(resultList));
    }
}
