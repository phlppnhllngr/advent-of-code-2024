package phlppnhllngr.adventofcode2024;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.IntFunction;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Day3MullItOver {

    public static void main(String[] args) {
        System.out.println(solvePart1());
        System.out.println(solvePart2());
    }

    static int evaluate(String input) {
        return Pattern.compile("(?<=mul\\()\\d{1,3},\\d{1,3}(?=\\))")
                .matcher(input)
                .results()
                .map(MatchResult::group)
                .map(group -> group.split(","))
                .mapToInt(arr -> Integer.parseInt(arr[0]) * Integer.parseInt(arr[1]))
                .sum();
    }

    static int evaluateWithConditionalsUsingRegex(String input) {
        var enabled = new AtomicBoolean(true);
        return Pattern.compile("(?<=mul\\()\\d{1,3},\\d{1,3}(?=\\))|(do(n't)?\\(\\))")
                .matcher(input)
                .results()
                .map(MatchResult::group)
                .mapToInt(group -> {
                    if ("do()".equals(group)) {
                        enabled.set(true);
                    } else if ("don't()".equals(group)) {
                        enabled.set(false);
                    } else if (enabled.get()) {
                        String[] parts = group.split(",");
                        return Integer.parseInt(parts[0]) * Integer.parseInt(parts[1]);
                    }
                    return 0;
                })
                .sum();
    }

    static int evaluateWithConditionalsReadingChars(String input) {
        var result = 0;
        var enabled = true;
        IntFunction<Character> safeCharAt = idx -> {
            if (idx < input.length()) return input.charAt(idx);
            return Character.MIN_VALUE;
        };

        for (int i = 0; i < input.length(); ) {
            if (safeCharAt.apply(i) == 'd') {
                if (safeCharAt.apply(i + 1) != 'o') {
                    i += 2;
                    continue;
                }
                if (!enabled && safeCharAt.apply(i + 2) == '(') {
                    if (safeCharAt.apply(i + 3) == ')') {
                        enabled = true;
                    }
                    i += 4;
                    continue;
                } else if (enabled && safeCharAt.apply(i + 2) == 'n') {
                    if (safeCharAt.apply(i + 3) != '\'') {
                        i += 4;
                        continue;
                    }
                    if (safeCharAt.apply(i + 4) != 't') {
                        i += 5;
                        continue;
                    }
                    if (safeCharAt.apply(i + 5) != '(') {
                        i += 6;
                        continue;
                    }
                    if (safeCharAt.apply(i + 6) == ')') {
                        enabled = false;
                    }
                    i += 7;
                    continue;
                } else {
                    i += 3;
                    continue;
                }
            } else if (enabled && safeCharAt.apply(i) == 'm') {
                if (safeCharAt.apply(i + 1) == 'u') {
                    if (safeCharAt.apply(i + 2) == 'l') {
                        if (safeCharAt.apply(i + 3) == '(') {
                            if (Character.isDigit(safeCharAt.apply(i + 4))) {
                                if (Character.isDigit(safeCharAt.apply(i + 5))) {
                                    if (Character.isDigit(safeCharAt.apply(i + 6))) {
                                        if (safeCharAt.apply(i + 7) == ',') {
                                            if (Character.isDigit(safeCharAt.apply(i + 8))) {
                                                if (Character.isDigit(safeCharAt.apply(i + 9))) {
                                                    if (Character.isDigit(safeCharAt.apply(i + 10))) {
                                                        if (safeCharAt.apply(i + 11) == ')') {
                                                            var multiplicand = Integer.parseInt(input.substring(i + 4, i + 7));
                                                            var multiplier = Integer.parseInt(input.substring(i + 8, i + 11));
                                                            result += multiplicand * multiplier;
                                                        }
                                                        i += 12;
                                                        continue;
                                                    } else if (safeCharAt.apply(i + 10) == ')') {
                                                        var multiplicand = Integer.parseInt(input.substring(i + 4, i + 7));
                                                        var multiplier = Integer.parseInt(input.substring(i + 8, i + 10));
                                                        result += multiplicand * multiplier;
                                                    }
                                                    i += 11;
                                                    continue;
                                                } else if (safeCharAt.apply(i + 9) == ')') {
                                                    var multiplicand = Integer.parseInt(input.substring(i + 4, i + 7));
                                                    var multiplier = Integer.parseInt(input.substring(i + 8, i + 9));
                                                    result += multiplicand * multiplier;
                                                }
                                                i += 10;
                                                continue;
                                            } else {
                                                i += 9;
                                                continue;
                                            }
                                        } else {
                                            i += 8;
                                            continue;
                                        }
                                    } else if (safeCharAt.apply(i + 6) == ',') {
                                        if (Character.isDigit(safeCharAt.apply(i + 7))) {
                                            if (Character.isDigit(safeCharAt.apply(i + 8))) {
                                                if (Character.isDigit(safeCharAt.apply(i + 9))) {
                                                    if (safeCharAt.apply(i + 10) == ')') {
                                                        var multiplicand = Integer.parseInt(input.substring(i + 4, i + 6));
                                                        var multiplier = Integer.parseInt(input.substring(i + 7, i + 10));
                                                        result += multiplicand * multiplier;
                                                    }
                                                    i += 11;
                                                    continue;
                                                } else if (safeCharAt.apply(i + 9) == ')') {
                                                    var multiplicand = Integer.parseInt(input.substring(i + 4, i + 6));
                                                    var multiplier = Integer.parseInt(input.substring(i + 7, i + 9));
                                                    result += multiplicand * multiplier;
                                                }
                                                i += 10;
                                                continue;
                                            } else if (safeCharAt.apply(i + 8) == ')') {
                                                var multiplicand = Integer.parseInt(input.substring(i + 4, i + 6));
                                                var multiplier = Integer.parseInt(input.substring(i + 7, i + 8));
                                                result += multiplicand * multiplier;
                                            }
                                            i += 9;
                                            continue;
                                        } else {
                                            i += 8;
                                            continue;
                                        }
                                    } else {
                                        i += 7;
                                        continue;
                                    }
                                } else if (safeCharAt.apply(i + 5) == ',') {
                                    if (Character.isDigit(safeCharAt.apply(i + 6))) {
                                        if (Character.isDigit(safeCharAt.apply(i + 7))) {
                                            if (Character.isDigit(safeCharAt.apply(i + 8))) {
                                                if (safeCharAt.apply(i + 9) == ')') {
                                                    var multiplicand = Integer.parseInt(input.substring(i + 4, i + 5));
                                                    var multiplier = Integer.parseInt(input.substring(i + 6, i + 9));
                                                    result += multiplicand * multiplier;
                                                }
                                                i += 10;
                                                continue;
                                            } else if (safeCharAt.apply(i + 8) == ')') {
                                                var multiplicand = Integer.parseInt(input.substring(i + 4, i + 5));
                                                var multiplier = Integer.parseInt(input.substring(i + 6, i + 8));
                                                result += multiplicand * multiplier;
                                            }
                                            i += 9;
                                            continue;
                                        } else if (safeCharAt.apply(i + 7) == ')') {
                                            var multiplicand = Integer.parseInt(input.substring(i + 4, i + 5));
                                            var multiplier = Integer.parseInt(input.substring(i + 6, i + 7));
                                            result += multiplicand * multiplier;
                                        }
                                        i += 8;
                                        continue;
                                    } else {
                                        i += 7;
                                        continue;
                                    }
                                } else {
                                    i += 6;
                                    continue;
                                }
                            } else {
                                i += 5;
                                continue;
                            }
                        } else {
                            i += 4;
                            continue;
                        }
                    } else {
                        i += 3;
                        continue;
                    }
                } else {
                    i += 2;
                    continue;
                }
            } else {
                i += 1;
                continue;
            }
        }
        return result;
    }

    static int solvePart1() {
        String input = readInput();
        return evaluate(input);
    }

    static int solvePart2() {
        String input = readInput();
        return evaluateWithConditionalsUsingRegex(input);
    }

    static String readInput() {
        try {
            byte[] bytes = Day3MullItOver.class.getResourceAsStream("/day3-input.txt").readAllBytes();
            return new String(bytes);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
