package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Md2Html {
    public static void main(String[] args) {
        var list = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(args[0]), StandardCharsets.UTF_8))) {
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Read file not found.");
            return;
        } catch (IOException e) {
            System.out.println("Error reading file");
            return;
        }
        Parser parser = new Parser(list);
        String res = parser.parse();
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            writer.write(res);
        } catch (FileNotFoundException e) {
            System.out.println("Write file not found.");
        } catch (IOException e) {
            System.out.println("Could not write to file.");
        }
    }
}
