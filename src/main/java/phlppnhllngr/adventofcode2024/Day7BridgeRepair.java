package phlppnhllngr.adventofcode2024;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * <a href="https://adventofcode.com/2024/day/7">Day 7</a>
 */
public class Day7BridgeRepair {

    public static void main(String[] args) {
        System.out.println(solvePart1());
    }

    static long calibrationResult(String input) {
        long totalCalibrationResult = 0;
        String[] lines = input.split("\r?\n");
        var random = new Random();
        lines: for (var line : lines) {
            String[] sides = line.split(": ");
            long value = Long.parseLong(sides[0]);
            String right = sides[1];
            Integer[] operatorIndices = allIndicesOf(' ', right);
            int numOperators = operatorIndices.length;

            Set<String> triedEquations = new HashSet<>();
            for (var numMultiplications = 0; numMultiplications <= numOperators;) {
                int numAdditions = numOperators - numMultiplications;
                long numPossibleEquations = factorial(numOperators) / (factorial(numAdditions) * factorial(numOperators - numAdditions));
                List<Integer> unsetOperatorIndices = new ArrayList<>(List.of(operatorIndices));
                var equationBuilder = new StringBuilder(right);

                while (numAdditions > 0) {
                    Integer randomUnsetOperatorIndex = unsetOperatorIndices.get(random.nextInt(unsetOperatorIndices.size()));
                    equationBuilder.setCharAt(randomUnsetOperatorIndex, '+');
                    unsetOperatorIndices.remove(randomUnsetOperatorIndex);
                    numAdditions--;
                }

                int numRemainingMultiplications = numMultiplications;
                while (numRemainingMultiplications > 0) {
                    Integer randomUnsetOperatorIndex = unsetOperatorIndices.get(random.nextInt(unsetOperatorIndices.size()));
                    equationBuilder.setCharAt(randomUnsetOperatorIndex, '*');
                    unsetOperatorIndices.remove(randomUnsetOperatorIndex);
                    numRemainingMultiplications--;
                }

                var equation = equationBuilder.toString();
                if (triedEquations.contains(equation)) {
                    continue;
                }
                long result = eval(equation);
                if (result == value) {
                    totalCalibrationResult += result;
                    continue lines;
                }
                else {
                    triedEquations.add(equation);
                }
                if (triedEquations.size() == numPossibleEquations) {
                    triedEquations.clear();
                    numMultiplications++;
                }
            }
        }

        return totalCalibrationResult;
    }

    static long solvePart1() {
        var input = Resources.readString("day7-input.txt");
        return calibrationResult(input);
    }

    private static Integer[] allIndicesOf(char c, String str) {
        List<Integer> indices = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (var i = 0; i < chars.length; i++) {
            if (chars[i] == c) {
                indices.add(i);
            }
        }
        return indices.toArray(Integer[]::new);
    }

    private static long factorial(int n) {
        if (n == 0 || n == 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private static long eval(String equation) {
        long[] numbers = Arrays.stream(equation.split("[*+]")).mapToLong(Long::parseLong).toArray();
        String[] operators = Arrays.stream(equation.split("\\d+")).filter(Predicate.not(String::isEmpty)).toArray(String[]::new);

        var result = numbers[0];

        for (var i = 0; i < operators.length; i++) {
            var op = operators[i];
            var num = numbers[i + 1];
            result = "+".equals(op) ? result + num : result * num;
        }

        System.out.println("Eval(" + equation + ") = " + result);
        return result;
    }
}
