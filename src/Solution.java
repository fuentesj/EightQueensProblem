import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jonathan on 3/24/18.
 */
public class Solution {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'}
        };
        solveEightQueensRecursively(board, 0, 0);
        for (char[] row : board) {
            for (char boardSquare : row) {
                System.out.print(boardSquare + " ");
            }
            System.out.print("\n");
        }
    }

    public static boolean solveEightQueensRecursively(char[][] board, int currentColumn, int numberOfQueensPlaced) {
        if (numberOfQueensPlaced >= 8) {
            return true;
        } else {
            List<Integer> candidates = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
            Iterator<Integer> candidateIterator = candidates.iterator();
            while (candidateIterator.hasNext()) {
                int currentRow = candidateIterator.next();
                boolean isSolvable = false;
                int origNumberOfQueensPlaced = numberOfQueensPlaced;
                int origCurrentColumn = currentColumn;
                if (!isQueenUnderAttack(board, currentRow, currentColumn)) {
                    moveQueen(board, currentRow, currentColumn);
                    numberOfQueensPlaced++;
                    currentColumn++;
                    isSolvable = solveEightQueensRecursively(board, currentColumn, numberOfQueensPlaced);
                }

                if (isSolvable) {
                    return true;
                } else {
                    resetBoard(board, currentColumn);
                    numberOfQueensPlaced = origNumberOfQueensPlaced;
                    currentColumn = origCurrentColumn;
                }
            }
            return false;
        }
    }

    private static void resetBoard(char[][] board, int currentColumn) {
        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
            for (int colIndex= board.length - 1; currentColumn <= colIndex; colIndex--) {
                if (board[rowIndex][colIndex] == 'Q') {
                    board[rowIndex][colIndex] = '.';
                }
            }
        }
    }

    private static void moveQueen(char[][] board, int endRow, int endCol) {
        for (int row = 0; row < board.length; row++) {
            board[row][endCol] = '.';
        }
        board[endRow][endCol] = 'Q';
    }

    private static boolean isQueenUnderAttack(char[][] board, int row, int col) {
        return checkIfQueenIsAttackedFromRow(board, row, col) || checkIfQueenIsAttackedFromDiagonals(board, row, col);
    }

    private static boolean checkIfQueenIsAttackedFromRow(char[][] board, int row, int col) {
        for (int colIndex = 0; colIndex < board.length; colIndex++) {
            if (col != colIndex &&
                    board[row][colIndex] == 'Q') {
                return  true;
            }
        }
        return false;
    }

    private static boolean checkIfQueenIsAttackedFromDiagonals(char[][] board, int row, int col) {
        return checkIfQueenIsAttackedBottomLeftToTopRightDiagonal(board, row, col) ||
        checkIfQueenIsAttackedTopLeftToBottomRight(board, row, col);
    }

    private static boolean checkIfQueenIsAttackedBottomLeftToTopRightDiagonal(char[][] board, int row, int col) {
        int rowIndexGoingUp = row;
        int colIndexGoingDown = col;
        while(rowIndexGoingUp < board.length && colIndexGoingDown >= 0) {
            if (rowIndexGoingUp != row &&
                    colIndexGoingDown != col &&
                        board[rowIndexGoingUp][colIndexGoingDown] == 'Q') {
                return true;
            }
            rowIndexGoingUp++;
            colIndexGoingDown--;
        }

        int rowIndexGoingDown = row;
        int colIndexGoingUp = col;
        while(rowIndexGoingDown >= 0 && colIndexGoingUp < board.length) {
            if (rowIndexGoingDown != row &&
                    colIndexGoingUp != col &&
                        board[rowIndexGoingDown][colIndexGoingUp] == 'Q') {
                return true;
            }
            rowIndexGoingDown--;
            colIndexGoingUp++;
        }
        return false;
    }

    private static boolean checkIfQueenIsAttackedTopLeftToBottomRight(char[][] board, int row, int col) {
        int rowIndexGoingDown = row;
        int colIndexGoingDown = col;
        while(rowIndexGoingDown < board.length && colIndexGoingDown < board.length) {
            if (rowIndexGoingDown != row &
                    colIndexGoingDown != col &&
                        board[rowIndexGoingDown][colIndexGoingDown] == 'Q') {
                return true;
            }
            rowIndexGoingDown++;
            colIndexGoingDown++;
        }

        int rowIndexGoingUp = row;
        int colIndexGoingUp = col;
        while(rowIndexGoingUp >= 0 && colIndexGoingUp >= 0) {
            if (rowIndexGoingUp != row &&
                    colIndexGoingUp != col &&
                        board[rowIndexGoingUp][colIndexGoingUp] == 'Q') {
                return true;
            }
            rowIndexGoingUp--;
            colIndexGoingUp--;
        }
        return false;
    }
}