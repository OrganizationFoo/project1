package asof191226;

class Solution {

	private int minPatternLength(String p) {
		int z = -1;
		while ((z = p.indexOf('*')) >= 0) {
			if (z == 0) {
				p = p.substring(1);
			} else {
				p = p.substring(0, (z - 1)) + p.substring(z + 1);
			}
		}
		return p.length();
	}

	public boolean isMatch(String s, String p) {

		int sl = s.length();
		int pl = p.length();

		// game over
		if (sl == 0 || pl == 0) {

			// p has wildcards remaining
			if (pl > 0) {
				pl = minPatternLength(p);
			}

			// the only true condition
			return (sl == pl);
		}

		char sx = s.charAt(0);
		char px = p.charAt(0) == '.' ? sx : p.charAt(0);

		boolean wildCard = p.length() > 1 && p.charAt(1) == '*';

		if (wildCard) {

			// optimization, summarize wildcard -------------------------------
			while (p.length() > 3
					// wildcard repeats, very expensive
					&& p.charAt(2) == p.charAt(0) && p.charAt(3) == '*') {
				p = p.substring(2);
			}
			// ----------------------------------------------------------------

			return

			// match, wildcard stays wild
			(sx == px && isMatch(s.substring(1), p))

					||

					// match, use up wildcard
					(sx == px && isMatch(s.substring(1), p.substring(2))

							||

							// ignore wildcard
							(isMatch(s, p.substring(2)))

					);

		} else if (sx == px) {

			// match, move along
			return isMatch(s.substring(1), p.substring(1));

		} else {

			// derp, failed
			return false;

		}
	}
}

public class Foo {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(" >>>>> \t\t\t1 t " + s.isMatch("aaa", "aaa"));
		System.out.println(" >>>>> \t\t\t2 f " + s.isMatch("aa", "aaa"));
		System.out.println(" >>>>> \t\t\t3 t " + s.isMatch("aaa", "a*aa"));
		System.out.println(" >>>>> \t\t\t4 t " + s.isMatch("aaa", "ab*ac*a"));
		System.out.println(" >>>>> \t\t\t5 t " + s.isMatch("aaa", "ab*ac*a*"));
		System.out.println(" >>>>> \t\t\t6 t " + s.isMatch("aaa", "ab*ac*a*"));
		System.out.println(" >>>>> \t\t\t7 t " + s.isMatch("aaa", "a****a****a***"));
		System.out.println(" >>>>> \t\t\t8 f " + s.isMatch("aa", "a"));
		System.out.println(" >>>>> \t\t\t9 t " + s.isMatch("aa", "a*"));
		System.out.println(" >>>>> \t\t\t0 t " + s.isMatch("ab", ".*"));
		System.out.println(" >>>>> \t\t\ta f " + s.isMatch("foo", "bar"));
		System.out.println(" >>>>> \t\t\tb t " + s.isMatch("mississippi", "mis*is*ip*."));
		System.out.println(" >>>>> \t\t\tc t " + s.isMatch("aaa", "a*a"));
		System.out.println(" >>>>> \t\t\tc t " + s.isMatch("a", "ab*"));
		System.out.println(" >>>>> \t\t\tz f " + s.isMatch("ab", ".*c"));
		System.out.println(" >>>>> \t\t\tz t " + s.isMatch("bbbba", ".*a*a"));
		System.out.println(" >>>>> \t\t\tz t " + s.isMatch("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*aaaaaaaac"));
	}
}
