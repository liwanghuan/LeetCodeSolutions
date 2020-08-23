/*
1553. Minimum Number of Days to Eat N Oranges
There are n oranges in the kitchen and you decided to eat some of these oranges every day as follows:

Eat one orange.
If the number of remaining oranges (n) is divisible by 2 then you can eat  n/2 oranges.
If the number of remaining oranges (n) is divisible by 3 then you can eat  2*(n/3) oranges.
You can only choose one of the actions per day.

Return the minimum number of days to eat n oranges.
*/
 
// Using DP in hashmap, array does not work in this case. 
class Solution {
    
    private Map<Integer, Integer> dp = new HashMap<Integer, Integer>();
    
    public int minDays(int n) {
        if (n <= 1) return n;
        if (!dp.containsKey(n)) dp.put(n, 1 + Math.min(n % 2 + minDays(n / 2), n % 3 + minDays(n / 3)));
        return dp.get(n);
    }
}