package aoc.day10;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
class Utils {

    private static final List<String> pairs = List.of("\\(\\)", "\\[\\]", "\\{\\}", "\\<\\>");

    static String removeClosedChunks(String line) {
        int currentSize = line.length();
        int previousSize = 0;

        // removing pairs
        while (currentSize != previousSize) {
            for (String pair : pairs) {
                line = line.replaceAll(pair, "");
            }
            previousSize = currentSize;
            currentSize = line.length();
        }
        return line;
    }

}
