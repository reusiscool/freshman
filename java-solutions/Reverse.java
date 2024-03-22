import CustomScanner.CustomScanner;

import java.util.Arrays;

public class Reverse{
    public static void main(String[] args) {
        CustomScanner scanner = new CustomScanner(System.in);
        int[] ints = new int[256];
        int len = 0;
        while (scanner.hasNext()) {
            int st = len;
            while (true) {
                if (len + 1 >= ints.length){
                    ints = Arrays.copyOf(ints, ints.length * 2);
                }
                if (!scanner.hasNextInLine()) {
                    break;
                }
                ints[len++] = scanner.nextInt();
            }
            ints[len++] = st;
        }
        while (len > 0) {
            int st = ints[--len];
            while (len > st){
                System.out.print(ints[--len]);
                System.out.print(" ");
            }
            System.out.println();
        }
        scanner.close();
    }
}