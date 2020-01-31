package asof191220;

import java.math.BigInteger;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

class Solution {

	private String reverseStringThis(ListNode n) {
		ListNode node = n;
		String s = "";
		while (node != null) {
			s = node.val + s;
			node = node.next;
		}
		return s;
	}

	private ListNode reverseListNodeThis(BigInteger x) {
		String s = x.toString();
		ListNode result = null;
		for (int i = (s.length() - 1); i >= 0; i--) {
			System.out.println(s.charAt(i));
			ListNode node = new ListNode(Integer.valueOf("" + s.charAt(i)));
			if (result == null) {
				result = node;
			} else {
				ListNode tmp = result;
				while (tmp.next != null) {
					tmp = tmp.next;
				}
				tmp.next = node;
			}
		}
		return result;
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		String a = reverseStringThis(l1);
		String b = reverseStringThis(l2);
		BigInteger c = (new BigInteger(a)).add(new BigInteger(b));
		return reverseListNodeThis(c);
	}
}

public class Foo {
	public static void main(String[] args) {
		Solution s = new Solution();
		// (2 -> 4 -> 3) + (5 -> 6 -> 4)
		ListNode x = new ListNode(2);
		ListNode y = new ListNode(4);
		ListNode z = new ListNode(3);
		x.next = y;
		y.next = z;

		ListNode a = new ListNode(5);
		ListNode b = new ListNode(6);
		ListNode c = new ListNode(4);
		a.next = b;
		b.next = c;

		ListNode result = s.addTwoNumbers(x, a);
		if (result != null) {
			System.out.println(" >>>>> ");
			ListNode tmp = result;
			do {
				System.out.print(tmp.val);
			} while ((tmp = tmp.next) != null);
			System.out.println();
		}
		System.out.println(" END");
	}
}
