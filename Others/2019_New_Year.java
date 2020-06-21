// Happy New Year 2019
/* 
2019 is the smallest number that can be written in 6 ways as the sum of the squares of 3 primes:
7² + 11² + 43² = 2019
7² + 17² + 41² = 2019
13² + 13² + 41² = 2019
11² + 23² + 37² = 2019
17² + 19² + 37² = 2019
23² + 23² + 31² = 2019
*/

class HappyNewYear {
	private static final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43}; // sqrt(2019) = 44 
	private static final int SUM = 2019;
	public static void main(String[] args) {
		System.out.println("2019 is the smallest number that can be written in 6 ways as the sum of the squares of 3 primes, they are:");
		int start , end;
		int n = primes.length;
		for (int i = 0; i < n; i++) {
			start = i;
			end = n-1;
			while (start < end) {
				int subSum = primes[i]*primes[i] + primes[start]*primes[start] + primes[end]*primes[end]; 
				if (subSum == SUM) {
					System.out.println("(" + Integer.toString(primes[i]) + ", " + Integer.toString(primes[start]) + ", " + Integer.toString(primes[end]) + ")");
					start++;
					end --;
				} else if (subSum < SUM) {
					start++;
				} else {
					end --;
				}
			}
		}
	}
}
