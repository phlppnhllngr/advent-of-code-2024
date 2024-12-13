package phlppnhllngr.adventofcode2024;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Day8ResonantCollinearityTest {

    String example = """
    ............
    ........0...
    .....0......
    .......0....
    ....0.......
    ......A.....
    ............
    ............
    ........A...
    .........A..
    ............
    ............
    """;

    @Test
    void part1_example() {
        assertEquals(Day8ResonantCollinearity.countUniqueAntinodeLocations(example), 14);
    }

    @Test
    void solves_part1() {
        assertEquals(Day8ResonantCollinearity.solvePart1(), 348);
    }

    @Test
    void part2_example() {
        assertEquals(Day8ResonantCollinearity.countUniqueAntinodeLocationsPart2(example), 34);
    }

    @Test
    void solves_part2() {
        assertEquals(Day8ResonantCollinearity.solvePart2(), 1221);
    }
}
