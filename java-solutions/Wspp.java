import CustomScanner.CustomScanner;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class Wspp {
    public static void main (String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong number of args");
            return;
        }
        try {
            CustomScanner scanner = new CustomScanner(args[0]);
            try {
                Map<String, IntList> map = read(scanner);
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

    private static void write (Map<String, IntList> map, BufferedWriter writer) throws IOException {
        for (var entry : map.entrySet()) {
            writer.write(entry.getKey());
            writer.write(" ");
            IntList ls = entry.getValue();
            writer.write(Integer.toString(ls.size()));
            writer.write(" ");
            for (int i = 0; i < ls.size(); i++) {
                writer.write(Integer.toString(ls.get(i)));
                if (i != ls.size() - 1) {
                    writer.write(' ');
                }
            }
            writer.newLine();
        }
    }

    private static Map<String, IntList> read (CustomScanner scanner) {
        Map<String, IntList> map = new LinkedHashMap<>();
        int index = 1;
        while (scanner.hasNextWord()) {
            String str = scanner.nextWord().toLowerCase();
            if (map.containsKey(str)) {
                map.get(str).add(index++);
            } else {
                IntList ls = new IntList();
                ls.add(index++);
                map.put(str, ls);
            }
        }
        return map;
    }
}