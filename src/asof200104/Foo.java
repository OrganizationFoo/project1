package asof200104;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

	public int threeSumClosest(int[] nums, int target) {
		int result = 0;
		int diff = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				for (int k = j + 1; k < nums.length; k++) {

					System.out.println(" > " + nums[i] + ", " + nums[j] + ", " + nums[k]);
					int x = target - (nums[i] + nums[j] + nums[k]);
					System.out.println("      x = " + x);
					if (x == 0) {
						// we found exact
						return target;
					} else {
						System.out.println("      is " + Math.abs(x) + " -> " + diff);
						if (Math.abs(x) < Math.abs(diff)) {
							System.out.println("      yes.");
							diff = x;
							result = (nums[i] + nums[j] + nums[k]);
						}
					}
				}
			}
		}
		return result;
	}

	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		int len = nums.length;
		for (int x1 = 0; x1 < len; x1++) {
			for (int x2 = x1 + 1; x2 < len; x2++) {
				for (int x3 = x2 + 1; x3 < len; x3++) {
					for (int x4 = x3 + 1; x4 < len; x4++) {
						if (nums[x1] + nums[x2] + nums[x3] + nums[x4] == target) {
							List<Integer> list = new ArrayList<>();
							list.add(nums[x1]);
							list.add(nums[x2]);
							list.add(nums[x3]);
							list.add(nums[x4]);
							Collections.sort(list);
							if (!result.contains(list)) {
								result.add(list);
							}
						}
					}
				}
			}
		}
		return result;
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode tmp = head;
		ListNode candidate = null;

		while (tmp != null) {
			if (n > 0) {
				n--;
			} else if (candidate == null) {
				candidate = head;
			} else {
				candidate = candidate.next;
			}
			tmp = tmp.next;
		}
		if (candidate != null) {
			candidate.next = candidate.next.next;
		} else {
			head = head.next;
		}
		return head;
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		ListNode result = new ListNode(-1);
		ListNode tail = result;

		while (l1 != null || l2 != null) {
			if (l1 != null && l2 != null) {
				if (l1.val < l2.val) {
					tail.next = l1;
					l1 = l1.next;
				} else {
					tail.next = l2;
					l2 = l2.next;
				}
			} else if (l1 != null) {
				tail.next = l1;
				l1 = l1.next;
			} else {
				tail.next = l2;
				l2 = l2.next;
			}
			tail.next.next = new ListNode(-1);
			tail = tail.next;
		}

		tail.next = null;
		return result.next;
	}

	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		ListNode result = new ListNode(-1);
		ListNode tail = result;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				tail.next = l1;
				l1 = l1.next;
			} else {
				tail.next = l2;
				l2 = l2.next;
			}
			tail = tail.next;
		}
		if (l1 != null) {
			tail.next = l1;
		} else {
			tail.next = l2;
		}
		return result.next;
	}

}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}

	public String toString() {
		String ret = "";
		ret += this.val;
		if (this.next != null) {
			ret += ", " + this.next.toString();
		}
		return ret;
	}
}

public class Foo {

	public static void main(String[] args) {
		Solution s = new Solution();
		// System.out.println(" >>>>> \t\t\t " + s.threeSumClosest(new int[] { -1, 2, 1,
		// -4 }, 1));
		// System.out.println(" >>>>> \t\t\t " + s.threeSumClosest(new int[] { 1, 1, 1,
		// 0 }, -100));
		// System.out.println(" >>>>> \t\t\t " + s.fourSum(new int[] { -5, 5, 4, -3, 0,
		// 0, 4, -2 }, 4));

		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		// System.out.println(" >>>>> \t\t\t " + s.removeNthFromEnd(head, 2));

		head = new ListNode(1);
		// System.out.println(" >>>>> \t\t\t " + s.removeNthFromEnd(head, 1));

		head = new ListNode(1);
		head.next = new ListNode(2);
		// System.out.println(" >>>>> \t\t\t " + s.removeNthFromEnd(head, 2));

		// 1->2->4, 1->3->4
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(4);

		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);
		System.out.println(" >>>>> \t\t\t " + s.mergeTwoLists(l1, l2));

	}
}
