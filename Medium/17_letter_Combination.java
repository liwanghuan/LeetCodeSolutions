
// 17. Letter Combination 
/**
Problem Description:

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note: Although the above answer is in lexicographical order, your answer could be in any order you want.

Analyse: use ascii number represents the index of button. For combination, use backtracking to update result list.

Time Complexity: O(4^n) where n is length of digits
**/

class Solution {
    public List<String> letterCombinations(String digits) {
        String[] phone = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < digits.length(); i++) {
            String button = phone[(int)digits.charAt(i) - 50];
            ArrayList<String> temp = new ArrayList<String>();
            for (int j = 0; j < button.length(); j++) {
                if (result.isEmpty()) {
                    temp.add(button.substring(j, j+1));
                } else {
                    for (int k = 0; k < result.size(); k++) {
                        temp.add(result.get(k)+button.substring(j, j+1));
                    }
                }
            }
            result = temp;
        }
        return result;
    }
}