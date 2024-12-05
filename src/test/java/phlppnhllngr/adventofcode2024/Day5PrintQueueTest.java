package phlppnhllngr.adventofcode2024;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Day5PrintQueueTest {

    String example =
        """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13
    
        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
        """;

    @Test
    void part1_example() {
        assertEquals(Day5PrintQueue.sumOfMiddlePageNumbersOfCorrectlyOrderedUpdates(example), 143);
    }

    @Test
    void solves_part1() {
        assertEquals(Day5PrintQueue.solvePart1(), 5391);
    }

    @Test
    void part2_example() { assertEquals(Day5PrintQueue.sumOfMiddlePageNumbersOfIncorrectlyOrderedUpdates(example), 123); }

    @Test
    void solves_part2() {
        assertEquals(Day5PrintQueue.solvePart2(), 6142);
    }
}
