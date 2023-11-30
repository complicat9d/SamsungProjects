public class Main {
    public static void main(String args[]) throws InterruptedException {
        Initialization.GameInitialization();
        boolean gameRunning = true;
        int win = 0;
        while (gameRunning) {
            win = Logic.Round();
            if (win != 0) {
                gameRunning = false;
            }
        }
        if (win == 1) {
            System.out.println("\033[H\033[2J");
            System.out.println(Initialization.nameFirstPlayer + " won the game!");
        } else if (win == 2) {
            System.out.println("\033[H\033[2J");
            System.out.println(Initialization.nameSecondPlayer + " won the game!");
        }
    }
}
