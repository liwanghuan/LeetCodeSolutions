/** 37. Sudoku Solver
Problem Description: 
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.

**/

// Method 1: Back Tracking 
class Solution {
    public void solveSudoku(char[][] board) {
        compute(board);
    }
    
    private boolean compute (char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (check(i, j, c, board)) {
                            board[i][j] = c;
                            if (compute(board)) return true;
                            else board[i][j] = '.';
                        }  
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean check(int i, int j, char c, char[][] board) {
        boolean result = true;
        for (int m = 0; m < 9 && result; m++) {
            result = (board[i][m] != c) & (board[m][j] != c) & 
                (board[3 * (i/ 3) + m / 3][3*(j/3) + m % 3] != c);

        }
        return result;
    }
}

// Method 2: 