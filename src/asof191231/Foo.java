package asof191231;

import java.util.ArrayList;
import java.util.List;

class Solution {

	private int min(int x, int y) {
		if (x < y) {
			return x;
		}
		return y;
	}

	public int maxArea(int[] height) {
		int max = 0;

		for (//
				int start = 0, //
				end = (height.length - 1); //
				start < end;) {

			int val = min(height[start], height[end]);
			int tmp = (end - start) * val;

			if (tmp > max) {
				max = tmp;
			}

			if (height[start] > height[end]) {
				// height at start is taller than at the end
				// move end closer to start if there is a height taller than height[end]
				boolean foundNextStart = false;
				for (int i = end - 1; i > start; i--) {
					if (height[i] > height[end]) {
						end = i;
						foundNextStart = true;
						break;
					}
				}
				if (!foundNextStart) {
					break;
				}
			} else {
				// height at end is taller than (or equal to) the start
				// move start closer to end if there is a height taller than height[start]
				boolean foundNextEnd = false;
				for (int i = start + 1; i < end; i++) {
					if (height[i] > height[start]) {
						start = i;
						foundNextEnd = true;
						break;
					}
				}
				if (!foundNextEnd) {
					break;
				}
			}
		}
		return max;
	}

	public enum Digit {

		X2(new char[] { 'a', 'b', 'c' }), //
		X3(new char[] { 'd', 'e', 'f' }), //
		X4(new char[] { 'g', 'h', 'i' }), //
		X5(new char[] { 'j', 'k', 'l' }), //
		X6(new char[] { 'm', 'n', 'o' }), //
		X7(new char[] { 'p', 'q', 'r', 's' }), //
		X8(new char[] { 't', 'u', 'v' }), //
		X9(new char[] { 'w', 'x', 'y', 'z' });

		char[] arr;

		private Digit(char[] arr) {
			this.arr = arr;
		}

		public static Digit get(char c) {
			switch (c) {
			case '2':
				return X2;
			case '3':
				return X3;
			case '4':
				return X4;
			case '5':
				return X5;
			case '6':
				return X6;
			case '7':
				return X7;
			case '8':
				return X8;
			case '9':
				return X9;
			default:
				throw new IllegalArgumentException("" + c);
			}
		}
	}

	public List<String> letterCombinations(String digits) {
		return letterCombinations(new ArrayList<String>(), digits, 0);
	}

	private List<String> letterCombinations(List<String> list, String digits, int index) {

		if (index >= digits.length()) {
			return list;
		}

		List<String> newList = new ArrayList<>();
		if (!list.isEmpty()) {
			for (char c : Digit.get(digits.charAt(index)).arr) {
				for (String str : list) {
					newList.add(str + c);
				}
			}
		} else {
			for (char c : Digit.get(digits.charAt(index)).arr) {
				newList.add("" + c);
			}
		}

		return letterCombinations(newList, digits, index + 1);
	}

	public String intToRoman(int num) {
		return intToRoman("", num);
	}

	private String intToRoman(String prefix, int x) {
		if (x == 0) {
			return prefix;
		}
		int d = 0;
		if (x >= (d = 1000)) {
			prefix += "M";
		} else if (x >= (d = 900)) {
			prefix += "CM";
		} else if (x >= (d = 500)) {
			prefix += "D";
		} else if (x >= (d = 400)) {
			prefix += "CD";
		} else if (x >= (d = 100)) {
			prefix += "C";
		} else if (x >= (d = 90)) {
			prefix += "XC";
		} else if (x >= (d = 50)) {
			prefix += "L";
		} else if (x >= (d = 40)) {
			prefix += "XL";
		} else if (x >= (d = 10)) {
			prefix += "X";
		} else if (x >= (d = 9)) {
			prefix += "IX";
		} else if (x >= (d = 5)) {
			prefix += "V";
		} else if (x >= (d = 4)) {
			prefix += "IV";
		} else if (x >= (d = 1)) {
			prefix += "I";
		}
		return intToRoman(prefix, (x - d));
	}

}

public class Foo {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(" >>>>> \t\t\t " + s.maxArea(new int[] { 1, 3, 2, 5, 25, 24, 5 }));
		System.out.println(" >>>>> \t\t\t " + s.letterCombinations("234"));
		System.out.println(" >>>>> \t\t\t " + s.intToRoman(2020));
		System.out.println(" >>>>> \t\t\t " + s.intToRoman(2000));
	}
}
