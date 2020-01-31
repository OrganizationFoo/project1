package asof200105;

import java.util.Arrays;

class Solution {

	static int activityNotifications(int[] expenditure, int d) {
		int notifications = 0;
		for (int i = d; i < expenditure.length; i++) {
			int x = expenditure[i];
			int median = foo(expenditure, i - d, d);
			if (x >= (median * 2)) {
				notifications++;
			}
		}
		return notifications;
	}

	static int foo(int[] arr, int from, int size) {
		int[] tmp = Arrays.copyOfRange(arr, from, from + size);
		Arrays.parallelSort(tmp);
		int half = size / 2;
		if (size % 2 == 0) {
			return (tmp[half] + tmp[half + 1]) / 2;
		} else {
			return tmp[half];
		}
	}
}

public class Foo {

	public static void main(String[] args) {
		System.out
				.println(" >>>>> \t\t\t " + Solution.activityNotifications(new int[] { 2, 3, 4, 2, 3, 6, 8, 4, 5 }, 5));

	}
}
