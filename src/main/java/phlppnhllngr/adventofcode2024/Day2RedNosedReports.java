package phlppnhllngr.adventofcode2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Day2RedNosedReports {

    public static void main(String[] args) {
        System.out.println(solvePart1());
        System.out.println(solvePart2());
    }

    static int countSafeReports(String data) {
        List<List<Integer>> reports = processData(data);
        var numSafeReports = 0;
        for (var report : reports) {
            if (checkSafeReport(report)) {
                numSafeReports++;
            }
        }
        return numSafeReports;
    }

    static int countSafeReportsWithTolerance(String data) {
        List<List<Integer>> reports = processData(data);
        var numSafeReports = 0;
        reports: for (var report : reports) {
            for (var i = 0; i < report.size(); i++) {
                var modifiedReport = new ArrayList<>(report);
                modifiedReport.remove(i);
                if (checkSafeReport(modifiedReport)) {
                    numSafeReports++;
                    continue reports;
                }
            }
        }
        return numSafeReports;
    }

    static int solvePart1() {
        String data = readData();
        return countSafeReports(data);
    }

    static int solvePart2() {
        String data = readData();
        return countSafeReportsWithTolerance(data);
    }

    private static String readData() {
        try {
            byte[] bytes = Day2RedNosedReports.class.getResourceAsStream("/day2-input.txt").readAllBytes();
            return new String(bytes);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static List<List<Integer>> processData(String data) {
        return Arrays.stream(data.split("\r?\n"))
            .map(line -> line.split(" "))
            .map(arr -> Arrays.stream(arr).map(Integer::parseInt).toList())
            .toList();
    }

    private static boolean checkSafeReport(List<Integer> report) {
        boolean decreased = false, increased = false;
        for (var i = 1; i < report.size(); i++) {
            var previousLevel = report.get(i - 1);
            var currentLevel = report.get(i);

            decreased = decreased || (currentLevel < previousLevel);
            increased = increased || (currentLevel > previousLevel);
            var delta = Math.abs(currentLevel - previousLevel);

            if ((decreased && increased) || delta > 3 || delta < 1) {
                return false;
            }
        }
        return true;
    }

}
