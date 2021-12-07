package aoc.day7;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

class Part1 {

    @SneakyThrows
    public static void main(String[] args) {
        var positions = Arrays.stream(Files.readAllLines(Path.of("src/main/resources/day7-input.txt"))
                .get(0)
                .split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        var min = positions.stream().min(Comparator.comparingInt(Integer::intValue)).get();
        var max = positions.stream().max(Comparator.comparingInt(Integer::intValue)).get();

        int minimalFuel = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++) {
            int finalI = i;
            var currentFuel = positions.stream()
                    .reduce(0, (sum, currentElement) -> sum + Math.abs(currentElement - finalI));
            if(currentFuel < minimalFuel) {
                minimalFuel = currentFuel;
            }
        }

        System.out.println("DONE");
        System.out.println(minimalFuel);
    }

}
