package asof191206;


public class Solution {

	static int[] merge(int[]... arrays) {
		int[] result = new int[] {};
		for (int[] array : arrays) {
			result = mergeTwo(array, result);
		}
		return result;
	}

	static int[] mergeTwo(int[] array1, int[] array2) {
		int index1 = 0;
		int index2 = 0;
		int[] result = new int[array1.length + array2.length];
		for(int i = 0; i < result.length; i++) {
			if(index1 == array1.length && index2 < array2.length) {
				result[i] = array2[index2];
				index2++;
			} else if (index2 == array2.length && index1 < array1.length) {
				result[i] = array1[index1];
				index1++;
			} else if(array1[index1] < array2[index2]) {
				result[i] = array1[index1];
				index1++;
			} else {
				result[i] = array2[index2];
				index2++;
			}
		}		
		return result;
	}

	public static void main(String[] args) {
		int[] merged = merge( //
				new int[] { 2, 3, 4, 5 }, //
				new int[] { 1, 3, 5, 7 }, //
				new int[] { 5, 7, 9 }, //
				new int[] { 2, 8 });
		for (int i : merged) {
			System.out.print(i + " ");
		}
	}
}
