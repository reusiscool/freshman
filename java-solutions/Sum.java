public class Sum {
	public static void main(String[] args) {
		int result = 0;
		for (String arg : args) {
			int power = 1;
			int cur_num = 0;
			for (int i = arg.length() - 1; i >= 0; i--) {
				char cur_symb = arg.charAt(i);
				if (!Character.isDigit(cur_symb)) {
					power = 1;
					result += cur_num * (cur_symb == '-' ? -1 : 1);
					cur_num = 0;
					continue;
				}
				cur_num += (cur_symb - '0') * power;
				power *= 10;
			}
			result += cur_num;
		}
		System.out.println(result);
	}
}