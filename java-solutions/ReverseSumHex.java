import java.util.Arrays;
import java.util.Scanner;

public class ReverseSumHex {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var row = new int[256];
        while (scanner.hasNextLine()) {
            var lineScanner = new Scanner(scanner.nextLine());
            int len = 0;
            int prefSum = 0;
            int previousNumber = 0;
            while (lineScanner.hasNext()) {
                if (len == row.length) {
                    row = Arrays.copyOf(row, len * 2);
                    Arrays.fill(row, len, len * 2, previousNumber);
                }
                prefSum += Integer.parseUnsignedInt(lineScanner.next(), 16);
                previousNumber = row[len];
                row[len++] += prefSum;
            }
            for (int i = 0; i < len; i++) {
                System.out.print(Integer.toHexString(row[i]));
                System.out.print(" ");
            }
            System.out.println();
            for (int i = len; i < row.length; i++) {
                row[i] += prefSum;
            }
        }
        scanner.close();
    }
}