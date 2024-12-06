package phlppnhllngr.adventofcode2024;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Day6GuardGallivantTest {

    String example =
        """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
        """;

    @Test
    void part1_example() {
        assertEquals(Day6GuardGallivant.countVisitedPositions(example), 41);
    }

    @Test
    void solves_part1() {
        assertEquals(Day6GuardGallivant.countVisitedPositions(example), 5461);
    }

}
