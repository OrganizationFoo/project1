package asof191219;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Pair {
	public final int left;
	public final int right;

	public Pair(int left, int right) {
		this.left = left;
		this.right = right;
	}

	public int hashcode() {
		return this.left + this.right;
	}

	public boolean equals(Object o) {
		if (o instanceof Pair) {
			Pair p = (Pair) o;
			return (this.left == p.left) && (this.right == p.right);
		}
		return false;
	}
}

class Solution_ {
	public List<List<Integer>> threeSum(int[] nums) {
		Set<List<Integer>> result = new HashSet<>();

		Map<Integer, Pair> map = new HashMap<>();
		Arrays.sort(nums);

		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;
		if (nums.length > 0) {
			min = nums[0];
			max = nums[nums.length - 1];
		}
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (map.containsKey(nums[j])) {
					Pair p = map.get(nums[j]);
					if (p.right != j && nums[j] >= nums[p.right]) {
						result.add(Arrays.asList(new Integer[] { nums[p.left], nums[p.right], nums[j] }));
					}
					continue;
				}
				int n = (nums[i] + nums[j]) * -1;
				if ((min <= n) && (max >= n)) {
					map.put(n, new Pair(i, j));
				}
			}
		}
		return new ArrayList<List<Integer>>(result);
	}
}

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums.length < 3) return result;
        int len = nums.length;
        int a, b, c;
        HashSet<Integer> codeSet = new HashSet<>();
        for(int i=0; i < len-2; i++) {
            int target = 0 - nums[i]; // (a+b+c = 0 => b+c = 0-a)
            a = nums[i];
            HashSet<Integer> set = new HashSet<>();
            for(int j=i+1; j < len; j++) {
                b = nums[j];
                if(set.contains(target-b)) {
                    c = target-b;
                       //add nums[i], nums[j], target-nums[j] to the result
                        ArrayList<Integer> aList =  new ArrayList<Integer>();
                            aList.add(a); 
                            aList.add(b);
                            aList.add(c);
                        Collections.sort(aList);
                        if(!codeSet.contains(aList.hashCode())) {
                            codeSet.add(aList.hashCode());
                            result.add(aList); 
                        } 
                    } 
                    set.add(b);
               
                }
        }
        return result;
   }
}

public class Foo {
	public static void main(String[] args) {
		Solution s = new Solution();
		List<List<Integer>> result = s.threeSum(new int[] { -1, 0, 1, 2, -1, -4 });
		for (List<Integer> r : result) {
			System.out.println(" >>>>> [" + r.get(0) + ", " + r.get(1) + ", " + r.get(2) + "]");
		}
	}
}
