import java.util.Arrays;

public class IntList {
    private int[] list = new int[2];
    private int size = 0;

    public void add(int value) {
        if (list.length <= size) {
            list = Arrays.copyOf(list, 2 * size);
        }
        list[size++] = value;
    }

    public int size() {
        return size;
    }

    public int get(int index) {
        return list[index];
    }
}
