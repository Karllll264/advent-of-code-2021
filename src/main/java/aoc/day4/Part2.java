package aoc.day4;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Part2 {


    @SneakyThrows
    public static void main(String[] args) {
        List<String> lines = Files.readAllLines(Path.of("src/main/resources/day4-input.txt"));
        int[] numbers = Arrays.stream(lines.get(0).split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<String> input = lines.subList(2, lines.size());
        List<Board> boards = new ArrayList<>();
        int index = 0;
        while(index < input.size()) {
            List<String> board = input.subList(index, index + 5);
            int [][] boardNumbers = new int[5][5];
            for(int i=0; i < board.size(); i++) {
                boardNumbers[i] = Arrays.stream(board.get(i).split(" +"))
                        .filter(line -> !line.isEmpty())
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
            boards.add(new Board(boardNumbers));
            index +=6;
        }

        for (int number : numbers) {
            System.out.println("Number: " + number);
            boards.forEach(board -> board.checkNumber(number));
            if(boards.size() == 1) {
                Board loosingBoard = boards.get(0);
                boolean hasBingo = loosingBoard.hasBingo();
                if(hasBingo) {
                    System.out.println("Winning board: " + Arrays.deepToString(loosingBoard.getBoard()));
                    System.out.println("Won: " + loosingBoard.getWinningSum() * number);
                    return;
                }
            }
            boards = boards.stream().filter(board -> !board.hasBingo())
                    .collect(Collectors.toList());
        }

        System.out.println("Done");

    }
}
