public class SumLongSpace {
	public static void main(String[] args) {
		long result = 0;
		for (String arg : args) {
			int l = 0;
			while (l < arg.length()) {
				while (l < arg.length() && Character.getType(arg.charAt(l)) == Character.SPACE_SEPARATOR) {
					l++;
				}
				int r = l;
				while (r < arg.length() && Character.getType(arg.charAt(r)) != Character.SPACE_SEPARATOR) {
					r++;
				}
				if (l != arg.length()) {
					result += Long.parseLong(arg.substring(l, r));
				}
				l = r;
			}
		}
		System.out.println(result);
	}
}