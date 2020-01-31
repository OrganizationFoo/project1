package asof200124;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
Given K sorted lists of up to N elements in each list, return a sorted iterator over all the items.

Important notes:
* K denotes the number of lists.
* N denotes the length of the longest list.
* You may assume K << N
* There aren't necessarily N items in each list, but there could be.
* Items are not unique, if an item appears X times (in a single list or in several lists), we would expect the iterator to return the item X consecutive times.

Receiving the following lists:

1: [1, 4, 5, 8, 9]
2: [3, 4, 4, 6]
3: [0, 2, 8]

Would yield the following result:

[0, 1, 2, 3, 4, 4, 4, 5, 6, 8, 8, 9]

*/

class IterateThrough implements Iterator<Integer> {

	private final List<List<Integer>> lists;
	private final List<Integer> indexes;

	public IterateThrough(final List<List<Integer>> lists) {
		this.lists = lists;
		this.indexes = new ArrayList<>();
		for (int i = 0; i < lists.size(); i++) {
			indexes.add(0);
		}
	}

	@Override
	public boolean hasNext() {
		boolean result = false;
		for (int i = 0; i < indexes.size(); i++) {
			if (lists.get(i).size() > indexes.get(i)) {
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public Integer next() {
		int min = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 0; i < indexes.size(); i++) {

			if (indexes.get(i) >= lists.get(i).size()) {
				continue;
			}

			int val = lists.get(i).get(indexes.get(i));
			if (val < min) {
				min = val;
				index = i;
			}
		}

		indexes.set(index, indexes.get(index) + 1);
		return min;
	}
}

public class Foo {

	public static void main(String[] args) {
		List<Integer> a = new ArrayList<>();
		a.add(1);
		a.add(4);
		a.add(5);
		a.add(8);
		a.add(9);

		List<Integer> b = new ArrayList<>();
		b.add(3);
		b.add(4);
		b.add(4);
		b.add(6);

		List<Integer> c = new ArrayList<>();
		c.add(0);
		c.add(2);
		c.add(8);

		List<List<Integer>> lists = new ArrayList<>();
		lists.add(a);
		lists.add(b);
		lists.add(c);

		IterateThrough x = new IterateThrough(lists);
		while ((new IterateThrough(lists)).hasNext()) {
			System.out.print(x.next() + " ");
		}
		System.out.println();
	}
}
