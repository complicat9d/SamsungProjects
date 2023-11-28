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
    public static void Round() {

    }
}

