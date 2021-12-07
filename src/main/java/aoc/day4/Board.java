package aoc.day4;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
class Board {

    int[][] board;
    List<List<Boolean>> foundNumbers;
    boolean hasBingo;
    int bingoRow = -1;
    int bingoCol = -1;


    public Board(int[][] board) {
        this.board = board;
        this.foundNumbers = new ArrayList<>(board.length);
        for(int i=0; i<board.length; i++) {
            this.foundNumbers.add(new ArrayList<>(board.length));
            this.foundNumbers.get(i).add(Boolean.FALSE);
            this.foundNumbers.get(i).add(Boolean.FALSE);
            this.foundNumbers.get(i).add(Boolean.FALSE);
            this.foundNumbers.get(i).add(Boolean.FALSE);
            this.foundNumbers.get(i).add(Boolean.FALSE);
        }
    }

    public void checkNumber(int number) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == number) {
                    foundNumbers.get(i).set(j, true);
                    return;
                }
            }
        }
    }

    public boolean hasBingo() {
        //rows
        for(int i=0; i < foundNumbers.size(); i++) {
            if (!foundNumbers.get(i).contains(Boolean.FALSE)) {
                bingoRow = i;
                return true;
            }
        }

        //columns
        for(int j=0; j<foundNumbers.get(0).size(); j++) {
            List<Boolean> tempColumn = new ArrayList<>();
            for (List<Boolean> foundNumber : foundNumbers) {
                tempColumn.add(foundNumber.get(j));
            }
            if (!tempColumn.contains(Boolean.FALSE)) {
                bingoCol = j;
                return true;
            }
        }
        return false;
    }


    public int getWinningSum() {
        int sum = 0;
        for(int i=0; i < board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                if(!foundNumbers.get(i).get(j)) {
                    sum += board[i][j];
                }
            }
        }
        return sum;
    }
}
