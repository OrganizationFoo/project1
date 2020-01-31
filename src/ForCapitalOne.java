import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ForCapitalOne {

	// Ryan Asuncion; Jan 15, 2020
	// ForCapitalOne

	public static final Comparator<Integer> MAX_PRIORITY = new Comparator<Integer>() {
		public int compare(Integer x, Integer y) {
			int result = 0;
			if (x < y) {
				result = 1;
			} else if (x > y) {
				result = -1;
			}
			return result;
		}
	};

	public static int minSum(List<Integer> num, int k) {
		// Write your code here

		// Queue that prioritizes max value
		PriorityQueue<Integer> queue = new PriorityQueue<>(MAX_PRIORITY);
		queue.addAll(num);

		Integer x = 0;
		while ((x = queue.poll()) != null) {
			System.out.print(x + " ");
		}
		System.out.println();

		for (int i = 0; i < k; i++) {

			// Pick max value to divide -> to maximize effect on minimizing sum
			int max = queue.poll();
			int ceil = (int) Math.ceil((double) max / 2);
			queue.add(ceil);
		}

		// Summing up result
		int result = 0;
		for (int i : queue) {
			result += i;
		}
		return result;
	}

	public static int findSmallestDivisor(String s, String t) {

		// Longer string s is not a repeating pattern of shorter string t's
		if (!isRepeatingPatternOf(s, t)) {
			return -1;
		}

		// Check string t for smaller repeating patterns
		String pattern = null;
		for (int i = 1; i <= (t.length() / 2); i++) {
			String substring = t.substring(0, i);

			if (t.indexOf(substring, i) > -1) {
				pattern = substring;

				// We have reached the end of the pattern and the start of the first match
				if (i == t.indexOf(substring, i) && isRepeatingPatternOf(t, pattern)) {

					// We only break if pattern is a repeating pattern of t
					break;
				}

			} else {
				break;
			}
		}

		int result = t.length();
		if (pattern != null && isRepeatingPatternOf(t, pattern)) {
			result = pattern.length();
		}
		return result;
	}

	private static boolean isRepeatingPatternOf(String str, String pattern) {
		StringBuilder tmp = new StringBuilder(pattern);
		while (tmp.length() < str.length()) {
			tmp.append(pattern);
		}
		return tmp.toString().equals(str);
	}

	public static void main(String[] args) {
//		List<Integer> list = new ArrayList<>();
//		for (int i = 0; i < 100; i++) {
//			list.add(i);
//		}
//		int minSum = minSum(list, 10000);
//		System.out.println(minSum);
		System.out.println(findSmallestDivisor("oooaoooaoooaoooa", "oooaoooa"));
	}
}
