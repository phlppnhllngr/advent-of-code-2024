package phlppnhllngr.adventofcode2024;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static phlppnhllngr.adventofcode2024.Day1HistorianHysteria.*;

public class Day1HistorianHysteriaTest {

    @Test
    void solves_part1() {
        assertEquals(solvePart1(), 1830467);
    }

    @Test
    void similarity_score_example() {
        assertEquals(31, similarityScore(
            new ArrayList<>(List.of(3, 4, 2, 1, 3, 3)),
            new ArrayList<>(List.of(4, 3, 5, 3, 9, 3))
        ));
    }

    @Test
    void solves_part2() {
        assertEquals(26674158, solvePart2());
    }

}
