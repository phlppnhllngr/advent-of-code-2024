package phlppnhllngr.adventofcode2024;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2024/day/6">Day 6</a>
 */
public class Day6GuardGallivant {

    public static void main(String[] args) {
        System.out.println(solvePart1());
    }

    static int countVisitedPositions(String map) {
        var labMap = LabMap.from(map);
        labMap.moveGuardUntilLeaving();
        return labMap.numVisitedPositions();
    }

    static int solvePart1() {
        var map = Resources.readString("day6-input.txt");
        return countVisitedPositions(map);
    }


    static class LabMap {
        int width;
        int height;
        List<Pos> positions;
        Guard guard;

        static LabMap from(String map) {
            var labMap = new LabMap();
            String[] lines = map.split("\r?\n");
            labMap.width = lines[0].length();
            labMap.height = lines.length;
            labMap.positions = new ArrayList<>(labMap.width * labMap.height);
            for (var y = 0; y < labMap.height; y++) {
                var line = lines[y];
                for (var x = 0; x < labMap.width; x++) {
                    char symbol = line.charAt(x);
                    Pos pos;
                    if (symbol == '.') {
                        pos = Pos.of(x, y);
                    }
                    else if (symbol == '#') {
                        pos = Pos.ofObstruction(x, y);
                    }
                    else if (symbol == '^') {
                        pos = Pos.ofVisited(x, y);
                        labMap.guard = new Guard(pos, "up");
                    }
                    else if (symbol == 'v') {
                        pos = Pos.ofVisited(x, y);
                        labMap.guard = new Guard(pos, "down");
                    }
                    else if (symbol == '<') {
                        pos = Pos.ofVisited(x, y);
                        labMap.guard = new Guard(pos, "left");
                    }
                    else {
                        pos = Pos.ofVisited(x, y);
                        labMap.guard = new Guard(pos, "right");
                    }
                    labMap.positions.add(pos);
                }
            }
            return labMap;
        }

        void moveGuardUntilLeaving() {
            while (true) {
                if (!moveGuard()) break;
            }
        }

        /**
         *
         * @return false when the guard is about to leave the lab
         */
        boolean moveGuard() {
            if (guard.direction.equals("up")) {
                Pos nextPos = positions.stream().filter(p -> p.x == guard.pos.x && p.y == guard.pos.y - 1).findFirst().get();
                if (nextPos.obstruction) {
                    guard.direction = "right";
                    return true;
                }
                guard.pos = nextPos;
                nextPos.visited = true;
                return guard.pos.y != 0;
            }
            if (guard.direction.equals("down")) {
                Pos nextPos = positions.stream().filter(p -> p.x == guard.pos.x && p.y == guard.pos.y + 1).findFirst().get();
                if (nextPos.obstruction) {
                    guard.direction = "left";
                    return true;
                }
                guard.pos = nextPos;
                nextPos.visited = true;
                return guard.pos.y != height - 1;
            }
            if (guard.direction.equals("left")) {
                Pos nextPos = positions.stream().filter(p -> p.x == guard.pos.x - 1 && p.y == guard.pos.y).findFirst().get();
                if (nextPos.obstruction) {
                    guard.direction = "up";
                    return true;
                }
                guard.pos = nextPos;
                nextPos.visited = true;
                return guard.pos.x != 0;
            }
            if (guard.direction.equals("right")) {
                Pos nextPos = positions.stream().filter(p -> p.x == guard.pos.x + 1 && p.y == guard.pos.y).findFirst().get();
                if (nextPos.obstruction) {
                    guard.direction = "down";
                    return true;
                }
                guard.pos = nextPos;
                nextPos.visited = true;
                return guard.pos.x != width - 1;
            }
            return false;
        }

        int numVisitedPositions() {
            return (int) positions.stream().filter(p -> p.visited).count();
        }
    }


    static class Pos  {

        int x;
        int y;
        boolean obstruction;
        boolean visited;

        Pos(int x, int y, boolean obstruction, boolean visited) {
            this.x = x;
            this.y = y;
            this.obstruction = obstruction;
            this.visited = visited;
        }

        static Pos of(int x, int y) {
            return new Pos(x, y, false, false);
        }
        static Pos ofObstruction(int x, int y) {
            return new Pos(x, y, true, false);
        }
        static Pos ofVisited(int x, int y) {
            return new Pos(x, y, false, true);
        }
    }


    static class Guard {

        Pos pos;
        String direction;

        Guard(Pos pos, String direction) {
            this.pos = pos;
            this.direction = direction;
        }
    }

}
