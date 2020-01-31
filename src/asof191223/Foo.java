package asof191223;

import java.util.HashMap;
import java.util.Map;

class Solution {
	public int lengthOfLongestSubstring(String s) {

		Map<Character, Integer> map = new HashMap<>();
		int resultMax = 0;
		int tmpMax = 0;
		int indexFrom = 0;

		// for each character in the string
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// hey, we found that character before
			if (map.containsKey(c)) {
				int lastIndexOf = map.get(c);

				// and that character appears after our last valid index (index_from)
				if (indexFrom <= lastIndexOf) {

					// tmp_max starts counting after that character's previous occurrence
					tmpMax = i - (lastIndexOf + 1);
					indexFrom = (lastIndexOf + 1);
				}
			}
			tmpMax++;
			if (tmpMax > resultMax) {
				resultMax = tmpMax;
			}
			map.put(c, i);
		}
		return resultMax;
	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // merged store
		int[] arr = new int[nums1.length + nums2.length];

        // indexes
        int i1 = 0;
		int i2 = 0;

        for (int i = 0; i < arr.length; i++) {
			if (i1 < nums1.length && i2 < nums2.length) {

                // we can compare arrays
				if (nums1[i1] < nums2[i2]) {
					arr[i] = nums1[i1];
					i1++;
				} else {
					arr[i] = nums2[i2];
					i2++;
				}

            } else if (i1 < nums1.length) {

                // there are more stuff left in nums1
				arr[i] = nums1[i1];
				i1++;

            } else {

                // there are more stuff left in nums2
                arr[i] = nums2[i2];
				i2++;

            }
		}

        if (arr.length % 2 == 0) {

            // middle is between these two
			int x = arr.length / 2;
			int y = x - 1;
			return (double) (arr[x] + arr[y]) / 2;

        } else {

            // middle is a number (odd length)
            return arr[arr.length / 2];

        }
	}

}

public class Foo {
	public static void main(String[] args) {
		Solution s = new Solution();
		// int result = s.lengthOfLongestSubstring("aabcdazxbdazq");
		// System.out.println(" >>>>> " + result);
		System.out.println(" >>>>> " + s.findMedianSortedArrays(new int[] { 1, 3 }, new int[] { 2, 2 }));
	}
}
