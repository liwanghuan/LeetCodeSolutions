// 15. 3 Sum
/*
Problem Descrption:Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
Note: The solution set must not contain duplicate triplets.

Example:
Given array nums = [-1, 0, 1, 2, -1, -4]
A solution set is:
[
[-1, 0, 1],
[-1, -1, 2]
]

4. Tag: Two pointers

5. Analyse: First sort the array. Then fix the first element (from 1 to n-2), use 2 pointers to iterate through the array.
Note that we need to delete the duplicate elements

6. Time Complexity: O(n^2)
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int n = nums.length;
        if (n < 3) return result;
        Arrays.sort(nums);
        int start, end;
        for (int i = 0; i < n-2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            start = i+1;
            end = n-1;
            while (start < end) {
                if (nums[i] + nums[start] + nums[end] == 0) {
                    result.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    start ++;
                    end --;
                    while (start < end && nums[start] == nums[start - 1]) start++; 
                    while (start < end && nums[end] == nums[end + 1]) end--;
                } else if (nums[i] + nums[start] + nums[end] > 0) {
                    end --;
                } else {
                    start ++;
                }
            }
        }
        return result;
    }
}