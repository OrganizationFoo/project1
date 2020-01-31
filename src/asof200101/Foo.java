package asof200101;

class Solution {
}

public class Foo {

	static long repeatedString(String s, long n) {

		int as = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'a') {
				as++;
			}
		}
		if (as == 0) {
			return 0;
		}
		long q = n / s.length();
		long result = as * q;
		System.out.println(" >>>>> q " + q);

		long r = n % s.length();
		if (r > 0) {
			System.out.println(" >>>>> " + r);
			result += repeatedString(s.substring(0, (int) r), r);
		}
		return result;
	}

	static int jumpingOnClouds(int[] c) {
		int jumps = 0;
		for (int i = 0; i < c.length - 1; i++) {
			// array length - 1, because we just jumped to the end
			if ((i + 1) < c.length && c[i + 1] == 1) {
				// next is a bad cloud, need to jump over
				i++;
				jumps++;
			} else if ((i + 2) < c.length && c[i + 2] == 0) {
				// can skip-jump
				i++;
				jumps++;
			} else {
				jumps++;
			}
		}
		return jumps;
	}

	public static void main(String[] args) {
		// System.out.println(" >>>>> \t\t\t " + repeatedString("Foo Bar Baz", 20));
		// System.out.println(" >>>>> \t\t\t " + repeatedString("aba", 10));
		System.out.println(" >>>>> \t\t\t " + jumpingOnClouds(new int[] { 0, 0, 1, 0, 0, 1, 0 }));

	}
}
