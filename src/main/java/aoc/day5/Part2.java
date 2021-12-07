package aoc.day5;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static aoc.day5.Utils.*;

class Part2 {

    private static int[][] ocean = new int[1000][1000];

    @SneakyThrows
    public static void main(String[] args) {
        List<String> lines = Files.readAllLines(Path.of("src/main/resources/day5-input.txt"));

        lines.stream()
                .map(Utils::prepareLine)
                .forEach(Part2::handleLine);

        System.out.println("Overlapping points: " + calculateOverlappingPoints(ocean));

    }

    private static void handleLine(Line line) {
        if(line.isHorizontal()) {
            handleHorizontal(line, ocean);
        } else if(line.isVertical()) {
            handleVertical(line, ocean);
        } else if(line.isDiagonal()) {
            handleDiagonal(line);
        }

    }

    private static void handleDiagonal(Line line) {
        Point startPoint = line.getStartPoint();
        Point endPoint = line.getEndPoint();

        if(startPoint.getX() > endPoint.getX()) {
            if(startPoint.getY() > endPoint.getY()) {
                for(int x = startPoint.getX(), y = startPoint.getY(); x >= endPoint.getX(); x--, y--) {
                    ocean[x][y]++;
                }
            } else {
                for(int x = startPoint.getX(), y = startPoint.getY(); x >= endPoint.getX(); x--, y++) {
                    ocean[x][y]++;
                }
            }
        } else {
            if(startPoint.getY() > endPoint.getY()) {
                for(int x = startPoint.getX(), y = startPoint.getY(); x <= endPoint.getX(); x++, y--) {
                    ocean[x][y]++;
                }
            } else {
                for(int x = startPoint.getX(), y = startPoint.getY(); x <= endPoint.getX(); x++, y++) {
                    ocean[x][y]++;
                }
            }
        }
    }

}
