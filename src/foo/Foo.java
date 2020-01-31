package foo;

public class Foo {

	public boolean isInterleave(String s1, String s2, String s3) {

		if (s3.length() != (s1.length() + s2.length())) {
			return false;
		}
		return isInterFoo(s1, s2, s3) || isInterFoo(s2, s1, s3);
	}

	public boolean isInterFoo(String s1, String s2, String s3) {
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (int i = 0; i < s1.length(); i++) {
			char c = s1.charAt(i);
			int tmp = s3.indexOf(c, index);
			if (tmp > -1) {
				sb.append(s3.substring(index, tmp));
				index = tmp + 1;
			}
		}
		if (index < s3.length()) {
			sb.append(s3.substring(index));
		}
		return sb.toString().equals(s2);
	}

	public boolean isInterleaving(int index1, int index2, int index3, String s1, String s2, String s3) {

		for (int i = index3; i < s3.length(); i++) {

			if (index1 >= s1.length()) {
				return s2.substring(index2).equals(s3.substring(i));
			}
			if (index2 >= s2.length()) {
				return s1.substring(index1).equals(s3.substring(i));
			}

			char c = s3.charAt(i);
			char c1 = s1.charAt(index1);
			char c2 = s2.charAt(index2);

			if (c1 == c && c2 != c) {
				index1++;
			} else if (c1 != c && c2 == c) {
				index2++;
			} else if (c1 == c && c2 == c) {
				return isInterleaving(index1 + 1, index2, i + 1, s1, s2, s3)
						|| isInterleaving(index1, index2 + 1, i + 1, s1, s2, s3);
			} else {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Foo foo = new Foo();

		System.out.println(foo.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
	}
}
