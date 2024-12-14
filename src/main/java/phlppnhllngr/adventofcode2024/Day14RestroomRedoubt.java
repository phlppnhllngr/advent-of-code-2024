package phlppnhllngr.adventofcode2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14RestroomRedoubt {

    public static void main(String[] args) {
        System.out.println(solvePart1());
    }

    static long safetyFactorAfter100Seconds(String robotData, int width, int height) {
        String[] lines = robotData.split("\r?\n");
        var pattern = Pattern.compile("p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)");
        var robots = new ArrayList<Robot>();

        for (var line : lines) {
            Matcher matcher = pattern.matcher(line.trim());
            matcher.find();
            var robot = new Robot();
            robot.x = Integer.parseInt(matcher.group(1));
            robot.y = Integer.parseInt(matcher.group(2));
            robot.vx = Integer.parseInt(matcher.group(3));
            robot.vy = Integer.parseInt(matcher.group(4));
            robots.add(robot);
        }
        for (var i = 0; i < 100; i++) {
            for (var robot : robots) {
                robot.x = robot.x + robot.vx;
                if (robot.x >= width) {
                    robot.x = robot.x - width;
                }
                else if (robot.x < 0) {
                    robot.x = width + robot.x;
                }
                robot.y = robot.y + robot.vy;
                if (robot.y >= height) {
                    robot.y = robot.y - height;
                }
                else if (robot.y < 0) {
                    robot.y = height + robot.y;
                }
            }
        }

        long q1 = 0, q2 = 0, q3 = 0, q4 = 0;
        for (var robot : robots) {
            boolean left = robot.x < width / 2;
            boolean right = robot.x > width / 2;
            boolean bottom = robot.y > height / 2;
            boolean top = robot.y < height / 2;
            if (left && top) q1++;
            else if (right && top) q2++;
            else if (right && bottom) q3++;
            else if (left && bottom) q4++;
        }

        return q1 * q2 * q3 * q4;
    }

    static long solvePart1() {
        var robotData = Resources.readString("day14-input.txt");
        return safetyFactorAfter100Seconds(robotData, 101, 103);
    }


    static class Robot {
        int x;
        int y;
        int vx;
        int vy;
    }
}
