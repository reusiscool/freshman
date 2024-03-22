import CustomScanner.CustomScanner;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatInput{
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong number of args");
            return;
        }
        try {
            CustomScanner scanner = new CustomScanner(args[0]);
            try {
                Map<String, Integer> map = read(scanner);
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
                    write(map, writer);
                }
            } finally {
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void write (Map<String, Integer> map, BufferedWriter writer) throws IOException {
        for (var entry : map.entrySet()) {
            writer.write(entry.getKey());
            writer.write(" ");
            writer.write(entry.getValue().toString());
            writer.newLine();
        }
    }

    private static Map<String, Integer> read(CustomScanner scanner) {
        Map<String, Integer> map = new LinkedHashMap<>();
        while (scanner.hasNextWord()) {
            String str = scanner.nextWord().toLowerCase();
            map.putIfAbsent(str, 0);
            map.replace(str, map.get(str) + 1);
        }
        return map;
    }
}