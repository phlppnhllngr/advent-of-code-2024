package phlppnhllngr.adventofcode2024;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Day4CeresSearchTest {

    static String example =
        """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
        """;

    @Test
    void part1_example() {
        assertEquals(Day4CeresSearch.countXmas(example), 18);
    }

    @Test
    void solves_part1() {
        assertEquals(Day4CeresSearch.solvePart1(), 2534);
    }

    @Test
    void part2_example() {
        assertEquals(Day4CeresSearch.countCrossMas(example), 9);
    }

    @Test
    void solves_part2() {
        assertEquals(Day4CeresSearch.solvePart2(), 1866);
    }
}
