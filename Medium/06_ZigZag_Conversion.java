/*
6. ZigZag Conversion: 

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
*/

class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        int numGroups = s.length()/(2*numRows-2) + 1;
        int numCols = (numRows-1)*numGroups;
        Character[][] matrix = new Character[numRows][numCols];
        for (int i = 0; i < s.length(); i++) {
            int groupID = i/(2*numRows - 2);
            int remainder = i % (2*numRows-2);
            if (remainder < numRows) {
               matrix[remainder][groupID * (numRows - 1)] =s.charAt(i);
            } else {
               matrix[2*(numRows-1) - remainder][(groupID-1)*(numRows-1)+remainder] = s.charAt(i);
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (matrix[i][j] != null) {
                    result.append(matrix[i][j]);
                }
            }
        }
        return result.toString();
    }
}