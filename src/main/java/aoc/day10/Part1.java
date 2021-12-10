package aoc.day10;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static aoc.day10.Utils.removeClosedChunks;

class Part1 {

    private static final List<Character> closingCharacters = List.of(')', ']', '}', '>');
    private static final Map<Character, Integer> characterValues = Map.of(
            ')', 3,
            ']', 57,
            '}', 1197,
            '>', 25137
    );

    @SneakyThrows(IOException.class)
    public static void main(String[] args) {
        var lines = Files.readAllLines(Path.of("src/main/resources/day10-input.txt"));
        int score = 0;
        for (var line : lines) {
            line = removeClosedChunks(line);

            score = addLineCorruptionScore(line, score);
        }

        System.out.println("Done");
        System.out.println(score); //265527
    }

    private static int addLineCorruptionScore(String line, Integer score) {
        for (char c : line.toCharArray()) {
            if (closingCharacters.contains(c)) {
                Integer characterValue = characterValues.get(c);
                score += characterValue;
                break;
            }
        }
        return score;
    }

}