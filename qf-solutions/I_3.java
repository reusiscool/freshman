package Contest;

import java.util.Scanner;

public class I {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long lx = 100_000_000L;
        long ly = 100_000_000L;
        long rx = -100_000_000L;
        long ry = -100_000_000L;
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            long x = scanner.nextLong();
            long y = scanner.nextLong();
            long h = scanner.nextLong();
            lx = Math.min(lx, x - h);
            rx = Math.max(rx, x + h);
            ly = Math.min(ly, y - h);
            ry = Math.max(ry, y + h);
        }
        System.out.print((lx + rx) / 2);
        System.out.print(" ");
        System.out.print((ly + ry) / 2);
        System.out.print(" ");
        long mx = Math.max(rx - lx, ry - ly);
        System.out.print(mx / 2 + mx % 2);
    }
}
