package asof191225;

import java.util.ArrayList;
import java.util.List;

class Solution {
	public String longestPalindrome(String s) {
		String result = s.length() > 0 ? "" + s.charAt(0) : "";
		for (int i = 0; i < s.length(); i++) {

			// We start at character at index i ...
			int start = i;

			// Check for repeating characters, same as at index i ...
			while ((i + 1) < s.length() && s.charAt(i) == s.charAt(i + 1)) {

				// We can increment index i if it just repeats the character ...
				i++;
			}

			// End index of repeating characters ...
			int end = i;

			if (((end + 1) - start) > result.length()) {

				// Repeating characters IS a palindrome, too
				result = s.substring(start, end + 1);
			}

			for (int p = 1; //

					// Moving index is still valid
					(start - p) >= 0 //
							&& (end + p) < s.length() //

							// Check equality for every additional character
							// before the start index and after the end index
							&& s.charAt(start - p) == s.charAt(end + p);

					p++) {

				// Is this palindrome longer than current result?
				String tmp = s.substring(start - p, end + p + 1);
				if (tmp.length() > result.length()) {
					result = tmp;
				}
			}
		}
		return result;
	}

	public String convert(String s, int numRows) {

		// Readying character lists for rows
		List<List<Character>> lists = new ArrayList<>();
		for (int i = 0; i < numRows; i++) {
			lists.add(new ArrayList<Character>());
		}

		// Variables used for zigzag direction
		int row = 0;
		boolean forward = true;

		for (int i = 0; i < s.length(); i++) {

			if (forward) {

				// Zigzag Forward direction
				row++;
				if (row == numRows) {
					forward = false;
				}
			} else if (row > 1) {

				// Watch out for numRows = 1,
				// if row is down to 1 -> can't change direction

				// Zigzag Reverse direction
				row--;
				if (row == 1) {
					forward = true;
				}
			}
			lists.get(row - 1).add(s.charAt(i));
		}

		// Read rows as written zigzag-ly
		int i = 0;
		char[] chars = new char[s.length()];
		for (List<Character> list : lists) {
			for (Character c : list) {
				chars[i++] = c;
			}
		}
		return new String(chars);
	}

	public int reverse(int x) {

		// Just simplifying in case negative
		boolean negative = false;
		if (x < 0) {
			negative = true;
			x *= -1;
		}

		// Reverse stringify
		String s = "" + x;
		String reverse = "";
		for (int i = s.length(); i > 0; i--) {
			reverse += "" + s.charAt(i - 1);
		}

		// Remove zero-prefix
		while (reverse.length() > 1 && reverse.startsWith("0")) {
			reverse = reverse.substring(1);
		}

		int result = 0;
		try {
			result = Integer.parseInt(reverse);
		} catch (Exception e) {
			//
		}

		// Negative handle
		if (negative) {
			result *= -1;
		}

		return result;
	}

	public int myAtoi(String str) {

		// Getting rid of trailing whitespaces
		str = str.trim();

		// This is our tmp store
		String tmp = "";

		// Simple nega handle (or plus/positive sign)
		boolean nega = false;
		if (str.length() > 0) {
			if (str.charAt(0) == '-') {
				nega = true;
				str = str.substring(1);
			} else if (str.charAt(0) == '+') {
				str = str.substring(1);
			}
		}

		// Char-Switcheroo
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			switch (c) {

			// Only cares for digits
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				tmp += "" + c;
				break;
			default:
				// Useless calling break on the switch, so we break the loop condition
				i = str.length();
			}
		}

		int result = 0;
		try {
			result = (tmp.length() > 0 ? Integer.parseInt(tmp) : 0) * (nega ? -1 : 1);
		} catch (Exception e) {
			// Special exception handle
			if (nega) {
				result = Integer.MIN_VALUE;
			} else {
				result = Integer.MAX_VALUE;
			}
		}
		return result;
	}
	
    public boolean isPalindrome(int x) {
        String s = "" +  x;
        int start = 0;
        int end = s.length() - 1;
        while(start < end) {
            if(s.charAt(start) == s.charAt(end)) {
                // all good ..
                start++;
                end--;
            } else {
                return false;
            }
        }
        return start == end || start == (end + 1);
    }
}

public class Foo {
	public static void main(String[] args) {
		Solution s = new Solution();
//		System.out.println(" >>>>> " + s.longestPalindrome("babab"));
//		System.out.println(" >>>>> " + s.longestPalindrome("babaab"));
//		System.out.println(" >>>>> " + s.longestPalindrome("babad"));
//		System.out.println(" >>>>> " + s.longestPalindrome("abba"));
//		System.out.println(" >>>>> " + s.longestPalindrome("zzzxxxabbazzaa"));
//		System.out.println(" >>>>> " + s.longestPalindrome("cbbd"));
//		System.out.println(" >>>>> " + s.longestPalindrome("aaaa"));
//		System.out.println(" >>>>> " + s.longestPalindrome("ccc"));
//		System.out.println(" >>>>> " + s.convert("PAYPALISHIRING", 3));
//		System.out.println(" >>>>> " + s.convert("PAYPALISHIRING", 4));
//		System.out.println(" >>>>> " + s.convert("AB", 1));
		System.out.println(" >>>>> " + s.reverse(123));
		System.out.println(" >>>>> " + s.reverse(1534236469));
		System.out.println(" >>>>> " + s.myAtoi("-999999999999"));
		System.out.println(" >>>>> " + s.myAtoi("  -  "));
		System.out.println(" >>>>> " + s.isPalindrome(121));
	}
}
