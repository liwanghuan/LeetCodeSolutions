/*
8. String to Integer (atoi)

Problem Description:
Implement atoi which converts a string to an integer. The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value. The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function. If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed. If no valid conversion could be performed, a zero value is returned.

Note: Only the space character ' ' is considered as whitespace character.

Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31, 2^31 − 1]. If the numerical value is out of the range of representable values, INT_MAX (2^31 − 1) or INT_MIN (−2^31) is returned.

Examples:
(1) Input: "42", Output: 42
(2) Input: " -42", Output: -42, Explanation: The first non-whitespace character is '-', which is the minus sign. Then take as many numerical digits as possible, which gets 42.
(3) Input: "4193 with words", Output: 4193, Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
(4) Input: "words and 987", Output: 0, Explanation: The first non-whitespace character is 'w', which is not a numerical digit or a +/- sign. Therefore no valid conversion could be performed.
(5) Input: "-91283472332", Output: -2147483648, Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.Thefore INT_MIN (−2^31) is returned.

Analyse: First trim the front white spaces and notice of stack overflow is OK.

Time Complexity: O(n) n is length of string.
*/

class Solution {
    public String trim(String str) {
        int start = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)!=' ') {
                start = i;
                break;
            }
        }
        return str.substring(start);
    } 
    public int myAtoi(String str) {
        // Trim the front of string
        str = trim(str);
        if (str.length() == 0) return 0;
        
        // Judge Positive or negative
        boolean isPositive = true;
        if (str.charAt(0) == '+') {
            str = str.substring(1);
        } else if (str.charAt(0) == '-') {
            isPositive = false;
            str = str.substring(1);
        } else {}
        
        // atoi it
        int end = str.length();
        for (int i = 0; i < str.length(); i++) {
            int currentAscii = (int) str.charAt(i);
            if (currentAscii > 57 || currentAscii < 48) {
                end = i;
                break;
            }
        }
        int result = 0;
        for (int i = 0; i < end; i++) {
            int next = (int)str.charAt(i) - 48;
            if (result > 214748364) {
                return isPositive? 2147483647 : -2147483648;
            } else if (result == 214748364) {
                if (next > 7) {
                    return isPositive? 2147483647 : -2147483648;
                } else {
                    result = result * 10 + next;
                    return isPositive? result : (-1)*result;
                }
            } else {
                result = result * 10 + next;
            }
        }
        return isPositive? result : (-1)*result;
    }
}