package aoc.day8;

import lombok.SneakyThrows;
import org.apache.commons.lang3.ArrayUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part1And2 {

    @SneakyThrows
    public static void main(String[] args) {
        Integer parsedNumber = Files.readAllLines(Path.of("src/main/resources/day8-input.txt"))
                .stream().map(Part1And2::processLine)
                .map(Integer::parseInt)
                .reduce(0, (sum, number) -> sum = sum + number);

        System.out.println("Done");
        System.out.println(parsedNumber); //1027422
    }

    private static String processLine(String line) {
        var inputDigits = line.split(" \\| ")[0].split(" ");
        var outputDigits = line.split(" \\| ")[1].split(" ");

        var inputs = Arrays.stream(inputDigits)
                .collect(Collectors.toMap(String::length, Function.identity(), (old, replacement) -> replacement));

        var seven = Arrays.stream(ArrayUtils.toObject(inputs.get(3).toCharArray())).collect(Collectors.toList());
        var four = Arrays.stream(ArrayUtils.toObject(inputs.get(4).toCharArray())).collect(Collectors.toList());
        var eight = Arrays.stream(ArrayUtils.toObject(inputs.get(7).toCharArray())).collect(Collectors.toList());

        return Arrays.stream(outputDigits)
                .map(outputDigit -> processOutputDigit(outputDigit, four, seven, eight))
                .reduce("", (string, part) -> string = string + part);
    }

    static String processOutputDigit(
            String outputDigit,
            List<Character> four,
            List<Character> seven,
            List<Character> eight) {
        var outputChars = Arrays.stream(ArrayUtils.toObject(outputDigit.toCharArray())).collect(Collectors.toList());
        switch (outputChars.size()) {
            case 2:
                return "1";
            case 3:
                return "7";
            case 4:
                return "4";
            case 5:
                // 2, 3, 5
                eight.removeAll(seven);
                eight.removeAll(four);
                if (outputChars.containsAll(seven) && !outputChars.containsAll(four)) {
                    return "3";
                } else if (!outputChars.containsAll(seven) && !outputChars.containsAll(four) && outputChars.containsAll(eight)) {
                    return "2";
                } else {
                    return "5";
                }
            case 6:
                // 0, 6, 9
                if (outputChars.containsAll(seven) && !outputChars.containsAll(four)) {
                    return "0";
                } else if (!outputChars.containsAll(seven) && !outputChars.containsAll(four)) {
                    return "6";
                } else {
                    return "9";
                }
            case 7:
                return "8";
            default:
                return "";
        }
    }

}
