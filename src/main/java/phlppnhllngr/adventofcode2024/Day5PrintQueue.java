package phlppnhllngr.adventofcode2024;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <a href="https://adventofcode.com/2024/day/5">Day 5</a>
 */
public class Day5PrintQueue {

    public static void main(String[] args) {
        System.out.println(solvePart1());
    }

    static int sumOfMiddlePageNumbersOfCorrectlyOrderedUpdates(String input) {
        var result = 0;

        String[] lines = input.split("\r?\n");
        Map<Integer, Set<Integer>> requiredBeforePerPage = new HashMap<>();

        lines: for (var line : lines) {
            if (line.isEmpty()) {
                continue;
            }

            var isPageOrderingRule = line.contains("|");
            if (isPageOrderingRule) {
                String[] parts = line.split("\\|");
                var left = Integer.parseInt(parts[0]);
                var right = Integer.parseInt(parts[1]);
                requiredBeforePerPage.compute(right, (key, list) -> {
                    if (list == null) {
                        list = new HashSet<>();
                    }
                    list.add(left);
                    return list;
                });
                continue;
            }

            List<Integer> update = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
            for (var i = 0; i < update.size(); i++) {
                Integer currentPage = update.get(i);
                Set<Integer> requiredBefore = requiredBeforePerPage.get(currentPage);
                if (requiredBefore == null || requiredBefore.isEmpty()) {
                    continue;
                }
                var nextPages = Set.of(update.subList(Math.min(update.size() - 1, i + 1), update.size()).toArray(Integer[]::new));
                if (nextPages.isEmpty()) {
                    continue;
                }
                var previousPages = Set.of(update.subList(0, i).toArray(Integer[]::new));
                for (var nextPage : nextPages) {
                    var requiredButNotSeenPreviously = requiredBefore.contains(nextPage) && !previousPages.contains(nextPage);
                    if (requiredButNotSeenPreviously) {
                        continue lines;
                    }
                }
            }
            result += update.get(update.size() / 2);
        }

        return result;
    }

    static int sumOfMiddlePageNumbersOfIncorrectlyOrderedUpdates(String input) {
        throw new UnsupportedOperationException();
    }

    static int solvePart1() {
        String input = Resources.readString("day5-input.txt");
        return sumOfMiddlePageNumbersOfCorrectlyOrderedUpdates(input);
    }

    static int solvePart2() {
        String input = Resources.readString("day5-input.txt");
        return sumOfMiddlePageNumbersOfIncorrectlyOrderedUpdates(input);
    }

}
