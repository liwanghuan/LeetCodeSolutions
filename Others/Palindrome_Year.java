/**
* Palindrome Year: 12 Palimndrome years like 20200202
**/

class PalindromeDateInMyLife {
	private static final int[] bigMonths = {1, 3, 5, 7, 8, 10, 12};
	private static final int[] smallMonths = {4, 6, 9, 11};
	private static final String[] monthNames = 
		{"January", "Februrary", "March", "April", "May", "June", 
		"July", "August", "September", "October", "November", "December"};
	
	public static void main(String[] args) {
		int count = 0;
		int birthYear = 1996;
		int deathYear = 2096; // Assume I can live 100 years >_<
		System.out.println("All Palindrome Dates in my Life: ");	
		System.out.println();
		System.out.println("1. China YYYY-MM-DD Format: ");
		for (int year = birthYear; year<=deathYear; year++) {
			// China Date Format
			if (isYMDFormatPalindrome(year)) {
				count ++;
				printYMDPalindrome(year);
			}
		}
		System.out.println("There Are " + count + " Palindrome Dates in YYYY-MM-DD Format");
		System.out.println();
		count = 0;
		System.out.println("2. Singapore DD-MM-YYYY Format: ");
		for (int year = birthYear; year<=deathYear; year++) {
			// Singapore Date Format
			if (isDMYFormatPalindrome(year)) {
				count ++;
				printDMYPalindrome(year);
			}
		}
		System.out.println("There Are " + count + " Palindrome Dates in DD-MM-YYYY Format");
	}
	
	private static void printYMDPalindrome(int year) {
		int month = 10*(year%10)+ (year%100)/10;
		int date = 10*(year/100%10) + year/1000;
		String result = String.valueOf(year) 
				+ new StringBuilder(String.valueOf(year)).reverse().toString();
		System.out.println(result + ": " + monthNames[month-1] + " " + 
				date + ", " + year);
	}
	
	private static void printDMYPalindrome(int year) {
		int date = 10*(year%10) + (year/10)%10;
		int month = 10*(year/100%10)+year/1000;
		String result = new StringBuilder(String.valueOf(year)).reverse().toString() 
				+ String.valueOf(year) ;
		System.out.println(result + ": " + monthNames[month-1] + " " + 
				date + ", " + year);
	}
	
	private static boolean isYMDFormatPalindrome(int year) {
		int month = 10*(year%10)+ (year%100)/10;
		int date = 10*(year/100%10) + year/1000;
		return isValidDate(year, month, date);
	}
	
	private static boolean isDMYFormatPalindrome(int year) {
		int date = 10*(year%10) + (year/10)%10;
		int month = 10*(year/100%10)+year/1000;
		return isValidDate(year, month, date);
	}
	
	private static boolean isValidDate(int year, int month, int date) {
		if (month == 2) {
			if (isLeapYear(year)) return date <= 29 && date >= 1;
			return date <= 28 && date >= 1;
		} else if (isBigMonth(month)) {
			return date<=31 && date >= 1;
		} else if (isSmallMonth(month)) {
			return date <= 30 && date >= 1;
		}
			
		return false;
	}
	
	private static boolean isLeapYear(int year) {
		if (year %100 == 0) {
			return year %400 == 0;
		}
		return year % 4 == 0;
	}
	
	private static boolean isBigMonth(int checkMonth) {
		for (int month: bigMonths) {
			if (checkMonth == month) return true;
		}
		return false;
	}
	
	private static boolean isSmallMonth(int checkMonth) {
		for (int month: smallMonths) {
			if (checkMonth == month) return true;
		}
		return false;
	}
}