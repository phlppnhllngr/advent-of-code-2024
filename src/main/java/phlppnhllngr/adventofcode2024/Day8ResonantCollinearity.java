package phlppnhllngr.adventofcode2024;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2024/day/8">Day 8</a>
 */
public class Day8ResonantCollinearity {

    public static void main(String[] args) {
        System.out.println(solvePart1());
    }

    static long countUniqueAntinodeLocations(String map) {
        String[] lines = map.split("\r?\n");
        var width = lines[0].length();
        var height = lines.length;
        List<Location> locations = new ArrayList<>(width * height);

        for (var l = 0; l < lines.length; l++) {
            var line = lines[l];
            for (var c = 0; c < line.length(); c++) {
                var loc = new Location();
                loc.x = c;
                loc.y = l;
                loc.symbol = line.charAt(c);
                locations.add(loc);
            }
        }

        long antinodes = 0;
        for (var thisLocation : locations) {
            if (!thisLocation.containsNode()) continue;
            for (var otherLocation : locations) {
                if (thisLocation == otherLocation || !otherLocation.containsNode() || thisLocation.symbol != otherLocation.symbol) {
                    continue;
                }
                int deltaX = thisLocation.x - otherLocation.x;
                int deltaY = thisLocation.y - otherLocation.y;
                for (var loc : locations) {
                    if (!loc.antinode
                        && ((loc.x == thisLocation.x + deltaX && loc.y == thisLocation.y + deltaY)
                        || (loc.x == otherLocation.x - deltaX && loc.y == otherLocation.y - deltaY))
                    ) {
                        loc.antinode = true;
                        antinodes++;
                    }
                }
            }
        }

        return antinodes;
    }

    static long solvePart1() {
        var map = Resources.readString("day8-input.txt");
        return countUniqueAntinodeLocations(map);
    }


    static class Location {
        int x;
        int y;
        char symbol;
        boolean antinode;

        boolean containsNode() {
            return this.symbol != '.';
        }
    }

}