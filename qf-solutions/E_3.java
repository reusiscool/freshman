package Contest;

import java.util.Arrays;
import java.util.Scanner;

public class E {
    private static IntList[] edges;
    private static boolean[] teamCities;

    private static class Pair {
        public int first;
        public int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    private static class IntList {
        private int[] list = new int[2];
        private int size = 0;

        void add(int value) {
            if (list.length <= size) {
                list = Arrays.copyOf(list, 2 * size);
            }
            list[size++] = value;
        }

        int pop() {
            return list[--size];
        }

        int size() {
            return size;
        }

        int getAt(int index) {
            return list[index];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        edges = new IntList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            edges[i] = new IntList();
        }
        for (int i = 0; i < n - 1; i++) {
            int v = scanner.nextInt();
            int u = scanner.nextInt();
            edges[u].add(v);
            edges[v].add(u);
        }
        teamCities = new boolean[n + 1];
        int start = 0;
        for (int i = 0; i < m; i++) {
            start = scanner.nextInt();
            teamCities[start] = true;
        }
        var ans = findMiddle(-1, start, new IntList());
        if (checkFit(-1, ans.second, ans.first / 2, 0)) {
            System.out.println("YES");
            System.out.println(ans.second);
        } else {
            System.out.println("NO");
        }
    }

    private static Pair findMiddle(int parent, int current, IntList path) {
        path.add(current);
        int maxDepth = teamCities[current] ? path.size() : -1;
        int middle = teamCities[current] ? path.getAt(path.size() / 2) : -1;
        for (int i = 0; i < edges[current].size(); i++) {
            if (edges[current].getAt(i) == parent) {
                continue;
            }
            var ans = findMiddle(current, edges[current].getAt(i), path);
            if (ans.first > maxDepth) {
                maxDepth = ans.first;
                middle = ans.second;
            }
        }
        path.pop();
        return new Pair(maxDepth, middle);
    }

    private static boolean checkFit(int parent, int current, int expected, int depth) {
        if (teamCities[current] && expected != depth) {
            return false;
        }
        for (int i = 0; i < edges[current].size(); i++) {
            if (edges[current].getAt(i) == parent) {
                continue;
            }
            if (!checkFit(current, edges[current].getAt(i), expected, depth + 1)) {
                return false;
            }
        }
        return true;
    }
}
