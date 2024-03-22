import CustomScanner.CustomScanner;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;
import java.util.TreeMap;

public class WsppSortedFirst {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong number of args");
            return;
        }

        SortedMap<String, Node> map;
        try {
            CustomScanner scanner = new CustomScanner(args[0]);
            try {
                map = read(scanner);
            } finally {
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Error. Could not open read file.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            write(map, writer);
        } catch (IOException e) {
            System.out.println("Error. Could not write.");
        }
    }

    private static void write(SortedMap<String, Node> map, BufferedWriter writer) throws IOException {
        for (var entry : map.entrySet()) {
            writer.write(entry.getKey());
            writer.write(" ");
            Node node = entry.getValue();
            writer.write(Integer.toString(node.wordCount));
            for (int i = 0; i < node.list.size(); i++) {
                writer.write(' ');
                writer.write(Integer.toString(node.list.get(i)));
            }
            writer.newLine();
        }
    }

    // :NOTE: Swallow errors
    private static SortedMap<String, Node> read(CustomScanner scanner) {
        SortedMap<String, Node> map = new TreeMap<>();
        int index = 0;
        int rowIndex = 0;
        while (scanner.hasNextWord()) {
            rowIndex++;
            while (scanner.hasNextWordInLine()) {
                String str = scanner.nextWord().toLowerCase();
                index++;
                Node node = map.get(str);
                if (node == null) {
                    node = new Node();
                    map.put(str, node);
                }
                node.wordCount++;
                if (node.lastRow != rowIndex) {
                    node.list.add(index);
                }
                node.lastRow = rowIndex;
            }
        }
        return map;
    }
}
