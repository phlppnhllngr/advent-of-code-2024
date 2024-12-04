package phlppnhllngr.adventofcode2024;

public class Day4CeresSearch {

    public static void main(String[] args) {
        System.out.println(solvePart1());
        System.out.println(solvePart2());
    }

    static int countXmas(String input) {
        char[][] grid = createGrid(input);
        var height = grid.length;
        var width = grid[0].length;

        var count = 0;

        for (var y = 0; y < height; y++) {
            for (var x = 0; x < width; x++) {
                char c = grid[y][x];
                if (c != 'X') continue;
                // →
                if (x + 3 < width && grid[y][x + 1] == 'M' && grid[y][x + 2] == 'A' && grid[y][x + 3] == 'S') {
                    count++;
                }
                // ↘
                if (x + 3 < width && y + 3 < height && grid[y + 1][x + 1] == 'M' && grid[y + 2][x + 2] == 'A' && grid[y + 3][x + 3] == 'S') {
                    count++;
                }
                // ↓
                if (y + 3 < height && grid[y + 1][x] == 'M' && grid[y + 2][x] == 'A' && grid[y + 3][x] == 'S') {
                    count++;
                }
                // ↙
                if (x >= 3 && y + 3 < height && grid[y + 1][x - 1] == 'M' && grid[y + 2][x - 2] == 'A' && grid[y + 3][x - 3] == 'S') {
                    count++;
                }
                // ←
                if (x >= 3 && grid[y][x - 1] == 'M' && grid[y][x - 2] == 'A' && grid[y][x - 3] == 'S') {
                    count++;
                }
                // ↖
                if (x >= 3 && y >= 3 && grid[y - 1][x - 1] == 'M' && grid[y - 2][x - 2] == 'A' && grid[y - 3][x - 3] == 'S') {
                    count++;
                }
                // ↑
                if (y >= 3 && grid[y - 1][x] == 'M' && grid[y - 2][x] == 'A' && grid[y - 3][x] == 'S') {
                    count++;
                }
                // ↗
                if (x + 3 < width && y >= 3 && grid[y - 1][x + 1] == 'M' && grid[y - 2][x + 2] == 'A' && grid[y - 3][x + 3] == 'S') {
                    count++;
                }
            }
        }

        return count;
    }

    static int countCrossMas(String input) {
        char[][] grid = createGrid(input);
        var height = grid.length;
        var width = grid[0].length;

        var count = 0;

        for (var y = 0; y < height; y++) {
            for (var x = 0; x < width; x++) {
                char c = grid[y][x];
                if (c != 'A') continue;
                if (x == 0 || x + 1 == width || y == 0 || y + 1 == height) continue;

                char topRight = grid[y - 1][x + 1];
                char bottomLeft = grid[y + 1][x - 1];
                if ((topRight == 'M' && bottomLeft == 'S') || (topRight == 'S' && bottomLeft == 'M')) {
                    char bottomRight = grid[y + 1][x + 1];
                    char topLeft = grid[y - 1][x - 1];
                    if ((bottomRight == 'M' && topLeft == 'S') || (bottomRight == 'S' && topLeft == 'M')) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    static char[][] createGrid(String input) {
        String[] lines = input.split("\r?\n");
        var height = lines.length;
        var width = lines[0].length();
        char[][] grid = new char[height][width];
        for (var l = 0; l < lines.length; l++) {
            grid[l] = lines[l].toCharArray();
        }
        return grid;
    }

    static int solvePart1() {
        String input = Resources.readString("day4-input.txt");
        return countXmas(input);
    }

    static int solvePart2() {
        String input = Resources.readString("day4-input.txt");
        return countCrossMas(input);
    }

}
