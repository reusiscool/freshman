package game;

public class Main {
    public static void main(String[] args) {
        int result;
        final Game game = new Game(false, new HumanPlayer(), new SequentialPlayer());
        Board b = new MnkBoard(3, 3, 3);
        do {
            b.clear();
            result = game.play(b);
            System.out.println("Game result: " + result);
        } while (result != 0);
    }
}
