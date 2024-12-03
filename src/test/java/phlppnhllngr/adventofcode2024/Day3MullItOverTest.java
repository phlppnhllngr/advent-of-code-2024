package phlppnhllngr.adventofcode2024;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Day3MullItOverTest {

    @Test
    void part1_example() {
        var input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
        assertEquals(Day3MullItOver.evaluate(input), 161);
    }

    @Test
    void solves_part() {
        assertEquals(Day3MullItOver.solvePart1(), 163931492);
    }

    @Test
    void part2_example() {
        var input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
        assertEquals(Day3MullItOver.evaluateWithConditionalsUsingParser(input), 48);
    }

    @Test
    void part_2_using_parser() {
        String input = Day3MullItOver.readInput();
        var before = System.currentTimeMillis();
        assertEquals(Day3MullItOver.evaluateWithConditionalsUsingParser(input), 76911921);
        System.out.println(System.currentTimeMillis() - before); // 4-5 ms
    }

    @Test
    void part_2_using_regex() {
        String input = Day3MullItOver.readInput();
        var before = System.currentTimeMillis();
        assertEquals(Day3MullItOver.evaluateWithConditionalsUsingRegex(input), 76911921);
        System.out.println(System.currentTimeMillis() - before); // 8-10 ms
    }

}
