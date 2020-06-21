// 12 Integer to Roman 
/*
1. Problem Description: 
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol Value
I 1
V 5
X 10
L 50
C 100
D 500
M 1000

For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.

Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.

2. Examples:
(1) Input: 3 Output: "III"
(2) Input: 4 Output: "IV"
(3) Input: 9 Output: "IX"
(4) Input: 58 Output: "LVIII" Explanation: L = 50, V = 5, III = 3.
(5) Input: 1994 Output: "MCMXCIV" Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

3. Tag: Recursion
4. Analyse: For each case, print quotient and recursively compute remainder
*/

class Solution {
    public String intToRoman(int num) {
        if (num >= 1000) {
            return print(num/1000, "M") + intToRoman(num%1000);
        } else if (num >= 900) {
            return "CM" + intToRoman(num-900);
        } else if (num >=500) {
            return print(num/500, "D") + intToRoman(num%500);
        } else if (num>=400) {
            return "CD" + intToRoman(num-400);
        } else if (num>=100) {
            return print(num/100, "C") + intToRoman(num%100);
        } else if (num>=90) {
            return "XC" + intToRoman(num-90);
        } else if (num>=50) {
            return print(num/50, "L") + intToRoman(num%50);
        } else if (num >=40) {
            return "XL" + intToRoman(num-40);
        } else if (num>=10) {
            return print(num/10, "X") + intToRoman(num%10);
        } else if (num>=9) {
            return "IX" + intToRoman(num-9);
        } else if (num>=5) {
            return print(num/5, "V") + intToRoman(num%5);
        } else if (num>=4){
            return "IV";
        } else {
            return print(num, "I");
        }
    }
    public String print(int n, String p) {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += p;
        }
        return result;
    }
}