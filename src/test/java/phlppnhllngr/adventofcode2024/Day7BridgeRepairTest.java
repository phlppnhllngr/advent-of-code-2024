package phlppnhllngr.adventofcode2024;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Day7BridgeRepairTest {

    String example = """
    190: 10 19
    3267: 81 40 27
    83: 17 5
    156: 15 6
    7290: 6 8 6 15
    161011: 16 10 13
    192: 17 8 14
    21037: 9 7 18 13
    292: 11 6 16 20
    """;

    @Test
    void part1_example() {
        assertEquals(Day7BridgeRepair.calibrationResult(example), 3749L);
    }

    @Test
    void solve_part1() {
        assertEquals(Day7BridgeRepair.solvePart1(), 1708857123053L);
    }
}
