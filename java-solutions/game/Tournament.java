package game;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class Tournament {
    private final boolean log;
    private Deque<Player> livePlayers;
    private Deque<Player> losers;
    private final Random random = new Random();
    private final List<Player> players;

    public Tournament (final boolean log, List<Player> players) {
        this.players = players;
        this.log = log;
    }

    public int play(Board board) {
        livePlayers = new ArrayDeque<>();
        for (var player : players) {
            livePlayers.push(player);
        }
        losers = new ArrayDeque<>();
        while (livePlayers.size() > 1) {
            playOneGame(board);
        }
        if (!log) {
            return returnPlayerStartPostition(livePlayers.peek());
        }
        losers.addFirst(livePlayers.removeFirst());
        int num = -1;
        for (Player player : losers) {
            if (num++ == -1) {
                log("#" + 1 + " " + player);
            } else {
                log("#" + (pow(2, log2(num)) + 1) + " " + player);
            }
        }
        return returnPlayerStartPostition(livePlayers.peek());
    }

    private int returnPlayerStartPostition(Player playerToFind) {
        int i = 0;
        for (var player : players) {
            i++;
            if (player == playerToFind) {
                return i;
            }
        }
        return -1;
    }

    private void playOneGame(Board board) {
        Player p1 = livePlayers.removeFirst();
        Player p2 = livePlayers.removeFirst();
        int res;
        do {
            Game game;
            int p1First = random.nextInt(1, 3);
            if (p1First == 1) {
                game = new Game(log, p1, p2);
            } else {
                game = new Game(log, p2, p1);
            }
            board.clear();
            res = game.play(board);
            if (res == p1First) {
                livePlayers.addLast(p1);
                losers.addFirst(p2);
            } else if (res == 3 - p1First) {
                livePlayers.addLast(p2);
                losers.addFirst(p1);
            }
        } while (res == 0);
    }

    private static int log2(int n) {
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    private static int pow(int base, int pow) {
        int res = 1;
        for (int i = 0; i < pow; i++) {
            res *= base;
        }
        return res;
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
