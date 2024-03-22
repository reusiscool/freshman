package Contest;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        long n = scanner.nextLong();
        if ((n - b) % (b - a) > 0) {
            System.out.println(2 * ((n - b) / (b - a) + 1) + 1);
        } else {
            System.out.println(2 * ((n - b) / (b - a)) + 1);
        }
    }
}
