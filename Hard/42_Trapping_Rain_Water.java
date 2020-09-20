// 42. Trapping Rain Water

/* Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

*/

// Analyse:
/*
Sum water amount of each bin(width=1).

Search from left to right and maintain a max height of left and right separately, which is like a one-side wall of partial container. Fix the higher one and flow water from the lower part. 

For example, if current height of left is lower, we fill water in the left bin. Until left meets right, we filled the whole container.
*/

// Solution:
class Solution {
    public int trap(int[] height) {
       int left = 0;
        int right = height.length - 1;
        int maxLeft = 0;
        int maxRight = 0;
        int result = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] >= maxLeft) maxLeft = height[left];
                else result += maxLeft - height[left];
                left ++;
            } else {
                if (height[right] >= maxRight) maxRight = height[right];
                else result += maxRight - height[right];
                right --;
            }
        }
        return result;
    }
} 