package Contest;

import java.util.Arrays;
import java.util.Scanner;

public class J {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] ls = new int[n][n];
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            res[i] = new int[n];
            String temp = scanner.next();
            for (int j = 0; j < n; j++) {
                ls[i][j] = Character.getNumericValue(temp.charAt(j));
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (ls[i][j] == 0)
                    continue;
                res[i][j] = 1;
                for (int k = j + 1; k < n; k++) {
                    ls[i][k] -= ls[j][k] - 10;
                    ls[i][k] %= 10;
                }
            }
        }
        for (var a : res) {
            for (var el : a)
                System.out.print(el);
            System.out.println();
        }
    }
}
