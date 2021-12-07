package aoc.day6;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

class Part1And2 {

    @SneakyThrows
    public static void main(String[] args) {

        var fishCollection = Arrays.stream(Files.readAllLines(Path.of("src/main/resources/day6-input.txt"))
                .stream()
                .findFirst()
                .get()
                .split(","))
                .map(Long::parseLong)
                .collect(groupingBy(Long::longValue, counting()));

        var days = 0;
        //for part 1 it was 80 days
        while(days < 256) {
            var tempMap = new HashMap<Long, Long>();
            for(long x = 0L; x <= 8L; x=x+1) {
                tempMap.put(x, 0L);
            }

            for (var entry: fishCollection.entrySet()) {
                if(entry.getKey() == 0L) {
                    //newborn fish
                    tempMap.put(8L, entry.getValue());
                    //old fish
                    tempMap.put(6L, entry.getValue());
                } else {
                    tempMap.put(entry.getKey() - 1, tempMap.get(entry.getKey() - 1) + entry.getValue());
                }
            }
            fishCollection = tempMap;
            days++;
        }

        var sum = fishCollection.values().stream()
                .reduce(0L, Long::sum);
        System.out.println(sum);
    }
}
