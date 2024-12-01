package phlppnhllngr.adventofcode2024;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1HistorianHysteria {

    static List<Integer> list1;
    static List<Integer> list2;

    static {
        readInput();
    }

    public static void main(String[] args) {
        System.out.println(solvePart1());
        System.out.println(solvePart2());
    }

    static int totalDistance(List<Integer> list1, List<Integer> list2) {
        Collections.sort(list1);
        Collections.sort(list2);

        var distance = 0;

        for (var i = 0; i < list1.size(); i++) {
            distance += Math.abs(list1.get(i) - list2.get(i));
        }

        return distance;
    }

    static long similarityScore(List<Integer> list1, List<Integer> list2) {
        Collections.sort(list1);
        Collections.sort(list2);

        long similarityScore = 0;

        for (int i = 0, j = 0; i < list1.size() && j < list2.size();) {
            int a = list1.get(i);
            int b = list2.get(j);

            if (a < b) {
                i++;
            }
            else if (a > b) {
                j++;
            }
            else {
                var num = a;
                int aCount = 1;
                for (var n = i + 1; n < list1.size(); n++) {
                    if (list1.get(n) == num) {
                        aCount++;
                    }
                    else {
                        i = n;
                        break;
                    }
                }
                int bCount = 1;
                for (var n = j + 1; n < list2.size(); n++) {
                    if (list2.get(n) == b) {
                        bCount++;
                    }
                    else {
                        j = n;
                        break;
                    }
                }
                similarityScore += ((long) num * aCount * bCount);
            }
        }

        return similarityScore;
    }

    static int solvePart1() {
        return totalDistance(list1, list2);
    }

    static long solvePart2() {
        return similarityScore(list1, list2);
    }

    static void readInput() {
        try (var is = Day1HistorianHysteria.class.getResourceAsStream("/day1-input.txt");
             var br = new BufferedReader(new InputStreamReader(is))
        ) {
            list1 = new ArrayList<>();
            list2 = new ArrayList<>();
            br.lines().map(line -> line.split("\\s+")).forEach(arr -> {
                list1.add(Integer.parseInt(arr[0]));
                list2.add(Integer.parseInt(arr[1]));
            });
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
