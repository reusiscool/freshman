package Contest;

import java.util.HashMap;
import java.util.Scanner;

public class M {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            long[] ls = new long[n];
            HashMap<Long, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                ls[i] = scanner.nextLong();
            }
            long res = 0;
            for (int j = n - 1; j > 0; j--) {
                for (int i = 0; i < j; i++) {
                    long ak = 2L * ls[j] - ls[i];
                    res += map.getOrDefault(ak, 0);
                }
                map.put(ls[j], map.getOrDefault(ls[j], 0) + 1);
            }
            System.out.println(res);
        }
    }
}
