package asof200102;

class Solution {

	public void zeroOnes(int[] arr) {
		int left = 0;
		int right = arr.length - 1;

		while (left < right) {
			if (arr[left] > arr[right]) {

				for (int i : arr) {
					System.out.print(i + " ");
				}
				System.out.println();

				arr[left] = 0;
				arr[right] = 1;
			}
			while (arr[left] == 0) {
				left++;
			}
			while (arr[right] == 1) {
				right--;
			}
			System.out.println("left = " + left);
			System.out.println("right = " + right);
		}
	}
}

public class Foo {

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] arr = new int[] { 1, 1, 1, 1, 0, 0 };
		s.zeroOnes(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}
}
