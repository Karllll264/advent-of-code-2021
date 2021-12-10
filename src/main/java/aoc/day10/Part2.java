package aoc.day10;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static aoc.day10.Utils.removeClosedChunks;

class Part2 {

    private static final List<Character> closingCharacters = List.of(')', ']', '}', '>');

    private static final Map<Character, Integer> characterValues = Map.of(
            ')', 1,
            ']', 2,
            '}', 3,
            '>', 4
    );

    private static final Map<Character, Character> characterPairs = Map.of(
            '(', ')',
            '[', ']',
            '{', '}',
            '<', '>'
    );


    @SneakyThrows(IOException.class)
    public static void main(String[] args) {
        var lines = Files.readAllLines(Path.of("src/main/resources/day10-input.txt"));
        var scores = new ArrayList<Long>();

        for (var line : lines) {
            line = removeClosedChunks(line);

            if (isCorrupted(line)) {
                continue;
            }

            var score = calculateScoreForLineCompletion(line);
            scores.add(score);
        }

        System.out.println("Done");
        System.out.println(getMiddleScore(scores)); //3969823589
    }

    private static boolean isCorrupted(String line) {
        for (char c : closingCharacters) {
            if (line.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }

    private static long calculateScoreForLineCompletion(String line) {
        long score = 0;
        for (int i = line.length() - 1; i >= 0; i--) {
            char current = line.charAt(i);
            Character closingCharacter = characterPairs.get(current);
            score = score * 5 + characterValues.get(closingCharacter);
        }
        return score;
    }

    private static long getMiddleScore(List<Long> scores) {
        var sortedScores = scores.stream()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
        return sortedScores.get(sortedScores.size() / 2);
    }

}