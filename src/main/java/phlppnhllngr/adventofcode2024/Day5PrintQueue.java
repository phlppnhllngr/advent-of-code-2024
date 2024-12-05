package phlppnhllngr.adventofcode2024;

import java.util.ArrayList;
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
        System.out.println(solvePart2());
    }

    static int sumOfMiddlePageNumbersOfCorrectlyOrderedUpdates(String input) {
        var result = 0;
        var parsedInput = readAndParseInput(input);

        updates: for (var update : parsedInput.updates()) {
            for (var i = 0; i < update.size(); i++) {
                Integer currentPage = update.get(i);
                Set<Integer> requiredBefore = parsedInput.requiredBeforePerPage().get(currentPage);
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
                        continue updates;
                    }
                }
            }
            result += update.get(update.size() / 2);
        }

        return result;
    }

    static int sumOfMiddlePageNumbersOfIncorrectlyOrderedUpdates(String input) {
        var result = 0;
        var parsedInput = readAndParseInput(input);

        for (var originalUpdate : parsedInput.updates()) {
            var orderedUpdate = putIntoCorrectOrder(originalUpdate, parsedInput.requiredBeforePerPage());
            var unchanged = Arrays.toString(originalUpdate.toArray()).equals(Arrays.toString(orderedUpdate.toArray()));
            if (!unchanged) {
                result += orderedUpdate.get(orderedUpdate.size() / 2);
            }
        }

        return result;
    }

    private static List<Integer> putIntoCorrectOrder(List<Integer> originalUpdate, Map<Integer, Set<Integer>> requiredBeforePerPage) {
        List<Integer> update = new ArrayList<>(originalUpdate);

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
                    update.remove(nextPage);
                    update.add(i, nextPage);
                    return putIntoCorrectOrder(update, requiredBeforePerPage);
                }
            }
        }

        return update;
    }

    static int solvePart1() {
        String input = Resources.readString("day5-input.txt");
        return sumOfMiddlePageNumbersOfCorrectlyOrderedUpdates(input);
    }

    static int solvePart2() {
        String input = Resources.readString("day5-input.txt");
        return sumOfMiddlePageNumbersOfIncorrectlyOrderedUpdates(input);
    }

    private static ParsedInput readAndParseInput(String input) {
        String[] lines = input.split("\r?\n");
        Map<Integer, Set<Integer>> requiredBeforePerPage = new HashMap<>();
        List<List<Integer>> updates = new ArrayList<>();

        for (var line : lines) {
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
            updates.add(update);
        }

        return new ParsedInput(requiredBeforePerPage, updates);
    }

    private record ParsedInput(Map<Integer, Set<Integer>> requiredBeforePerPage, List<List<Integer>> updates) {}

}
