package phlppnhllngr.adventofcode2024;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Day2RedNosedReportsTest {

    @Test
    void safe_reports_example() {
        var data = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
        """;

        assertEquals(Day2RedNosedReports.countSafeReports(data), 2);
    }

    // L995-L1000
    @Test
    void safe_reports_input_sample_all_safe() {
        var data = """
        62 64 66 69 71 73 76 79
        30 31 33 36 39 40 41
        55 54 52 51 48 45 43
        40 43 45 46 47 50
        48 46 43 42 40 38
        87 84 83 80 77 76
        """;

        assertEquals(Day2RedNosedReports.countSafeReports(data), 6);
    }

    @Test
    void solves_part1() {
        assertEquals(Day2RedNosedReports.solvePart1(), 334);
    }

    @Test
    void safe_reports_with_tolerance_example() {
        var data = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
        """;

        assertEquals(Day2RedNosedReports.countSafeReportsWithTolerance(data), 4);
    }

    @Test
    void safe_reports_with_tolerance() {
        var data = """
        1 5 6 7 8
        1 4 2 3 4
        1 2 6 3 4
        1 2 3 7 4
        1 2 4 5 9
        """;

        assertEquals(Day2RedNosedReports.countSafeReportsWithTolerance(data), 5);
    }

    @Test
    void solves_part2() {
        assertEquals(Day2RedNosedReports.solvePart2(), 400);
    }

}
