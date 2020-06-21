// 5. Longest Palindrome 
/*
Problem Description:Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Examples:
(1) Input: "babad" Output: "bab" Note: "aba" is also a valid answer.
(2) Input: "cbbd" Output: "bb"
*/

// Analyse:
// (1) Dynamic Programming: Create a truth table, Check from 3 to n, if previous one is palindrome, start from this.

// Time Complecity: O(n^2)

class Solution {
    public String longestPalindrome(String s) {
        if (s.length() == 0) return "";
        int n = s.length();
        boolean[][] table = new boolean[n][n];
        int maxLength = 1;
        for (int i = 0; i < n; i++) {
            table[i][i] = true;
        }
        int start = 0;
        for (int i = 0; i < n-1; i++) {
            if (s.charAt(i) == s.charAt(i+1)){
                table[i][i+1] = true;
                start = i;
                maxLength = 2;
            }
        }
        
        for (int k = 3; k <=n; k++) {
            for (int i = 0; i < n-k+1; i++) {
                int j = i+k-1;
                if (table[i+1][j-1] && s.charAt(i) == s.charAt(j)) { 
                    table[i][j] = true;  
                    start = i; 
                    maxLength = k;
                }
            }
        }
        
        return s.substring(start, start+maxLength);
    }
}

/*
(2) Manacher's Algorithm:

c is the position of the center of the palindrome currently known to include a boundary closest to the right end of s2 (i.e., the length of the palindrome = p[c]×2+1)

r is the position of the right-most boundary of this palindrome (i.e., r = c + p[c])

i is the position of an element (i.e., a character or boundary) in s2 whose palindromic span is being determined, with i always to the right of c

Time Complexity: O(n*k) n is length of string and k is length of longest palindrome substring
*/

class Solution {
    public char[] addBoundaries(char[] cs) {
        if (cs==null || cs.length==0) {
            return "||".toCharArray();
        }
        
        char[] cs2 = new char[cs.length*2+1];
        for (int i = 0; i<(cs2.length-1); i = i+2) {
            cs2[i] = '|';
            cs2[i+1] = cs[i/2];
        }
        cs2[cs2.length-1] = '|';
        return cs2;
    }
    public char[] removeBoundaries(char[] cs) {
        if (cs==null || cs.length<3) {
            return "".toCharArray();
        }
        char[] cs2 = new char[(cs.length-1)/2];
        for (int i = 0; i<cs2.length; i++) {
            cs2[i] = cs[i*2+1];
        }
        return cs2;
    }    
    public String longestPalindrome(String s) {
        if (s==null || s.length()==0) return "";
        
        char[] s2 = addBoundaries(s.toCharArray());
        int[] p = new int[s2.length]; 
        int c = 0, r = 0; // Here the first element in s2 has been processed.
        int m = 0, n = 0; // The walking indices to compare if two elements are the same
        for (int i = 1; i<s2.length; i++) {
            if (i>r) {
                p[i] = 0; m = i-1; n = i+1;
            } else {
                int i2 = c*2-i;
                if (p[i2]<(r-i-1)) {
                    p[i] = p[i2];
                    m = -1; // This signals bypassing the while loop below. 
                } else {
                    p[i] = r-i;
                    n = r+1; m = i*2-n;
                }
            }
            while (m>=0 && n<s2.length && s2[m]==s2[n]) {
                p[i]++; m--; n++;
            }
            if ((i+p[i])>r) {
                c = i; r = i+p[i];
            }
        }
        int len = 0; c = 0;
        for (int i = 1; i<s2.length; i++) {
            if (len<p[i]) {
                len = p[i]; c = i;
            }
        }
        char[] ss = Arrays.copyOfRange(s2, c-len, c+len+1);
        return String.valueOf(removeBoundaries(ss));
    }
}