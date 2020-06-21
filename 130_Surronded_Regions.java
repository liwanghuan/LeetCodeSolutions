// 130. Surronded Regions
/**
Problem Description: Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example: 
X X X X
X O O X
X X O X
X O X X

After running your function, the board should be:
X X X X
X X X X
X X X X
X O X X
 **/

// JAVA solution: 
/* Idea: Change Side regions from 'O' to '1' and finally change O-X 1-0
Knowledge: DP
Origin: 
X X X X
X O O X
X X O X
X O X X

After tracing:
X X X X
X O O X
X X O X
X 1 X X

Finally:
X X X X
X X X X
X X X X
X O X X

*/

class Solution {
    public void solve(char[][] board) {
        int m = board.length;
        if (m == 0) return;
        int n = board[0].length;
        // Tracing board 'O'
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
               trace(i, 0, m, n, board);
            }

            if (board[i][n-1] == 'O') {
                trace(i, n-1, m, n, board);
            } 
        }
        
        for (int j = 1; j<n-1; j++) {
            if (board[0][j] == 'O') {
                trace(0, j, m, n, board);
            } 
            
            if (board[m-1][j] == 'O') {
                trace(m-1, j, m, n, board);
            }
        }
        
        // Do final change
        for (int i =0; i < m; i++) {
            for (int j = 0; j <n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
        
    }
    
   private void trace(int i, int j, int m, int n, char[][] board) {
       board[i][j] = '1';
       if (i < m-1 && board[i+1][j] == 'O') {
           trace(i+1, j, m, n, board);
       }
       
       if (j < n-1 && board[i][j+1] == 'O') {
           trace(i, j+1, m, n, board);
       }
       
       if (i > 0 && board[i-1][j] == 'O') {
           trace(i-1, j, m, n, board);
       } 
       
       if (j > 0 && board[i][j-1] == 'O') {
           trace(i, j-1, m, n, board);
       }
   }
}