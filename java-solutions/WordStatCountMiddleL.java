import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatCountMiddleL{
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong number of args");
            return;
        }
        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8))) {
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
                    Map<String, Integer> map = read(reader);
                    write(map, writer);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void write (Map<String, Integer> map, BufferedWriter writer) throws IOException {
        var entries = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
        for (var entry : entries) {
            writer.write(entry.getKey());
            writer.write(" ");
            writer.write(entry.getValue().toString());
            writer.newLine();
        }
    }

    private static Map<String, Integer> read(Reader reader) throws IOException {
        Map<String, Integer> map = new LinkedHashMap<>();
        char[] buffer = new char[1024];
        StringBuilder sb = new StringBuilder();
        int read = reader.read(buffer);
        while (read >= 0) {
            for (int i = 0; i < read; i++) {
                char c = buffer[i];
                if (Character.getType(c) == Character.DASH_PUNCTUATION || Character.isAlphabetic(c) || c == '\''){
                    sb.append(c);
                    continue;
                }
                if (!sb.isEmpty()) {
                    String str = sb.toString().toLowerCase();
                    if (str.length() >= 5){
                        String sub = str.substring(2 , str.length() - 2);
                        map.put(sub, map.getOrDefault(sub, 0) + 1);
                    }
                    sb.setLength(0);
                }
            }
            System.out.println();
            read = reader.read(buffer);
        }
        return map;
    }
}