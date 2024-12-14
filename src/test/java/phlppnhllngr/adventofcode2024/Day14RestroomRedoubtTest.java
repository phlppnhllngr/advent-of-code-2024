package phlppnhllngr.adventofcode2024;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Day14RestroomRedoubtTest {

    String example =
        """
        p=0,4 v=3,-3
        p=6,3 v=-1,-3
        p=10,3 v=-1,2
        p=2,0 v=2,-1
        p=0,0 v=1,3
        p=3,0 v=-2,-2
        p=7,6 v=-1,-3
        p=3,0 v=-1,-2
        p=9,3 v=2,3
        p=7,3 v=-1,2
        p=2,4 v=2,-3
        p=9,5 v=-3,-3
        """;

    @Test
    void part1_example() {
        assertEquals(Day14RestroomRedoubt.safetyFactorAfter100Seconds(example, 11, 7), 12L);
    }

    @Test
    void solves_part1() {
        assertEquals(Day14RestroomRedoubt.solvePart1(), 216772608L);
    }
}
