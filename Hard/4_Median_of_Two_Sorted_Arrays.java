// 4. Median of Two Sorted Arrays
/*
1. Problem Description:
There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)). You may assume nums1 and nums2 cannot be both empty.
2. Examples:
(1) nums1 = [1, 3] nums2 = [2] The median is 2.0
(2) nums1 = [1, 2] nums2 = [3, 4] The median is (2 + 3)/2 = 2.5
3. Tag: Binary Search, Divide and Conquer
4. Analyse: We assume N = nums1.length <= M = nums2.length
Base Cases:
Case 0: N = 0 -> Return median of nums2. if M = 0, return -1.
Case 1: N = 1, M = 1 -> return median
Case 2: N = 1, M is odd -> Total number is even.
Let a = median of nums1, b = median of nums2, b-1 and b+1 be the left and right element of b. The middle 2 is chosen from (b-1, b), (a, b), (b, a) or (b, b-1). 

Method 1: We can call M04(a, b-1, b+1, b)
Method 2: We noticed no matter where a located, b must be chosen. Another one is the median of a, b-1, b+1
Case 3: N = 1, M is even -> Total number is odd. Median is a or b-0.5 or b+0.5. -> M03(a, b-1, b+1)
Case 4: N = 2, M = 2 -> call M04(nums1[0], nums1[1], nums2[0], nums1[1])
Case 5: N = 2, M is odd -> No matter where we insert 2 elements in nums1 ino nums2, median must be chosen from one of these 5 numbers: a-0.5, a+0.5, b, b-1, b+1.
We can observe: (1) must not be the min or max of these 5 (2) b is in the middle of b-1 and b+1, so b must not be max or min
So we only need to calculate M03 of middle 3 numbers.
Case 6: N = 2, M is even -> Consider: a-0.5, a+0.5, b-0.5, b+0.5, b-1.5, b+1.5 these 6 numbers, median is the average of middle 2.
Similar with case 5, delete max and min, M04 of middle 4 numbers.
General Cases: M > N > 2
1) Find the median position of A[] and median position of B[].
1.1) If the middle item of A[] is greater than middle item of B[], ignore the last half of A[], let length of ignored part is idx. Also, cut down B[] by idx from the start.
1.2) else, ignore the first half of A[], let length of ignored part is idx. Also, cut down B[] by idx from the last.
Notice of general cases:
1) Everytime we only half the length of A. In order to make sure that after deletion, the position of median does not change, A and B must delete same amount of elements
2) We must remain the position of median. When n (length of nums1) is even, remain the middle 2 elements and delete other n/2-1; When n is odd, remain the middle element and delete other n/2.
5. Time Complexity: 0(log(min(m, n)))
*/

class Solution {
    public double M02 (int a, int b) {
        return (a+b)/2.0;
    }
    public double M03 (int a, int b, int c) {
        return a + b + c - Math.max(a, Math.max(b, c)) - Math.min(a, Math.min(b, c));
    }
    public double M04 (int a, int b, int c, int d) {
        int max = Math.max(a, Math.max(b, Math.max(c, d)));
        int min = Math.min(a, Math.min(b, Math.min(c, d)));
        return (a + b + c + d - max - min)/2.0;
    }
    
    public double medianSingle(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            return -1;
        } else {
            if (n % 2 == 0) {
                return (arr[n/2] + arr[n/2 -1])/2.0;
            } else {
                return (double)arr[n/2];
            }
        }
    }

    public double findMedianUtil (int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        if (n == 0) {
            return medianSingle(B);
        } else if (n == 1) {
            if (m == 1) {
                return M02(A[0], B[0]);
            } else if (m % 2 == 1) {
                return M02(B[m/2], (int)M03(A[0], B[m/2+1], B[m/2-1]));
            } else {
                return M03(B[m/2-1], B[m/2], A[0]);
            }
        } else if (n == 2) {
            if (m == 2) {
                return M04(A[0], A[1], B[0], B[1]);
            } else if (m % 2 == 1) {
                return M03(B[m/2], 
                           Math.max(A[0], B[m/2-1]), 
                           Math.min(A[1], B[m/2+1]));
            } else {
                return M04(B[m/2], B[m/2-1], 
                           Math.max(A[0], B[m/2-2]), Math.min(A[1], B[m/2+1]));
            }
        } else {
            double medianA = medianSingle (A);
            double medianB = medianSingle (B);
            int discard = n % 2 == 0? n/2-1 : n/2;
            if (medianA <= medianB) {
                return findMedianUtil(Arrays.copyOfRange(A, discard, n), 
                                      Arrays.copyOfRange(B, 0, m-discard));
            } else {
                return findMedianUtil(Arrays.copyOfRange(A, 0, n-discard), 
                                      Arrays.copyOfRange(B, discard, m));
            }
        }
    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianUtil (nums2, nums1);
        } else {
            return findMedianUtil (nums1, nums2);
        }
    }
}