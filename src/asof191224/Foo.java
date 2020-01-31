package asof191224;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Point {
	final int x;
	final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int hashCode() {
		return x;
	}

	public boolean equals(Object o) {
		if (o instanceof Point) {
			Point tmp = (Point) o;
			return this.x == tmp.x && this.y == tmp.y;
		}
		return false;
	}
}

class Solution {
	public int solve(Collection<Point> points) {
		int result = 0;
		Map<Integer, List<Integer>> x_map_of_ys = new HashMap<>();
		Map<Integer, List<Integer>> y_map_of_xs = new HashMap<>();
		for (Point point : points) {
			int x = point.x;
			int y = point.y;
			if (x_map_of_ys.containsKey(x)) {
				// we have an existing point that would form a line relative to x axis ...
				List<Integer> list = x_map_of_ys.get(x);
				if (!list.contains(y)) {
					// ... only if y differs
					list.add(y);
				}
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(y);
				x_map_of_ys.put(x, list);
			}
			if (y_map_of_xs.containsKey(y)) {
				// we have an existing point that would form a line relative to y axis ...
				List<Integer> list = y_map_of_xs.get(y);
				if (!list.contains(x)) {
					// ... only if x differs
					list.add(x);
				}
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(x);
				y_map_of_xs.put(y, list);
			}
		}

		for (int x : x_map_of_ys.keySet()) {
			List<Integer> yList = x_map_of_ys.get(x);
			for (int y : yList) {
				if(y_map_of_xs.containsKey(y)) {
					List<Integer> xList = y_map_of_xs.get(y);
					for(int x2 : xList) {
						if(x2 != x) {
							System.out.println(" >>>>> x = " + x + "; y = " + y + " -> " + x2);
						}
					}
				}
			}
		}
		return result;
	}
}

public class Foo {
	public static void main(String[] args) {
		Solution s = new Solution();
		int count = s.solve(Arrays.asList(new Point[] { //
				new Point(1, 1), new Point(1, 2), //
				new Point(2, 1), new Point(2, 2), //
				new Point(3, 1), new Point(3, 2) }));
		System.out.println(" >>>>> " + count);
	}
}
