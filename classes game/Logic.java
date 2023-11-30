public class Logic {
    public static int WhoWon() {
        int whoWon = 0;
        Unit firstUnits[] = Initialization.firstPlayerWarriors;
        Unit secondUnits[] = Initialization.secondPlayerWarriors;
        if (firstUnits[0].CheckIfDead() && firstUnits[1].CheckIfDead() && firstUnits[2].CheckIfDead()) {
            whoWon = 2;
        } else if (secondUnits[0].CheckIfDead() && secondUnits[1].CheckIfDead() && secondUnits[2].CheckIfDead()) {
            whoWon = 1;
        }
        return whoWon;
    }
    /*Implement Round(), so it describes one round of, firstly, the first player attacking the second one
     * and, secondly, vice versa + printing out info about hits
     * Implement PrintPlayersWarriorsStats()
     */
    public static int roundCounter = 0;
    public static int Round() throws InterruptedException{
        System.out.println("\033[H\033[2J");
        System.out.println("------Round " + ++roundCounter + "------");
        System.out.println(Initialization.nameFirstPlayer + "'s warriors:");
        for (int i = 0; i < 3; i++) {
            Unit.PrintCommonCharacteristics(Initialization.firstPlayerWarriors[i]);
        }
        System.out.println(Initialization.nameSecondPlayer + "'s warriors:");
        for (int i = 0; i < 3; i++) {
            Unit.PrintCommonCharacteristics(Initialization.secondPlayerWarriors[i]);
        }
        Thread.sleep(10000);
        System.out.println("\033[H\033[2J");
        System.out.println(Initialization.nameFirstPlayer + " is attacking");
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; j++) {
                Unit.PrintCommonCharacteristics(Initialization.secondPlayerWarriors[j]);
            }
            if (WhoWon() != 0) {
                return WhoWon();
            }
            Initialization.firstPlayerWarriors[i].Attack(Unit.ChooseEnemyManually("first"));
        }
        for (int i = 0; i < 3; i++) {
            Unit.PrintCommonCharacteristics(Initialization.secondPlayerWarriors[i]);
        }
        Thread.sleep(2000);
        System.out.println("\033[H\033[2J");
        System.out.println(Initialization.nameSecondPlayer + " is attacking");
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; j++) {
                Unit.PrintCommonCharacteristics(Initialization.firstPlayerWarriors[j]);
            }
            if (WhoWon() != 0) {
                return WhoWon();
            }
            Initialization.secondPlayerWarriors[i].Attack(Unit.ChooseEnemyManually("second"));
        }
        for (int i = 0; i < 3; i++) {
            Unit.PrintCommonCharacteristics(Initialization.firstPlayerWarriors[i]);
        }
        Thread.sleep(2000);
        return 0;
    }
}

