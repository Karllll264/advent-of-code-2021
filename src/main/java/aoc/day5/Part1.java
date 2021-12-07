package aoc.day5;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static aoc.day5.Utils.*;

class Part1 {

    private static int[][] ocean = new int[1000][1000];

    @SneakyThrows
    public static void main(String[] args) {
        List<String> lines = Files.readAllLines(Path.of("src/main/resources/day5-input.txt"));

        lines.stream()
            .map(Utils::prepareLine)
            .filter(Line::isVerticalOrHorizontal)
            .forEach(Part1::handleLine);

        System.out.println("Overlapping points: " + calculateOverlappingPoints(ocean));

    }

    private static void handleLine(Line line) {
        if(line.isHorizontal()) {
            handleHorizontal(line, ocean);
        } else if(line.isVertical()) {
            handleVertical(line, ocean);
        }
    }

}
