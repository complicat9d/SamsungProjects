import java.util.Random;
import java.util.Scanner;

public class Unit extends Initialization {
    final static double GRID_SEGMENTS = 25;
    double armour, health, attack, chanceToParry;
    Unit(double armour, double health, double attack) {
        this.armour = armour;
        this.health = health;
        this.attack = attack;
    }
    public static void ShowBar (double stat, double maxStat, String color) {
        double filledSegements = stat / (maxStat / GRID_SEGMENTS);
        for (int i = 0; i < Math.round(filledSegements); i++) {
            System.out.print(color + "░" + Colors.ANSI_RESET);
        } 
        for (int i = 0; i < Math.round(GRID_SEGMENTS - filledSegements); i++) {
            System.out.print("░");
        }
        System.out.format("  %.1f / %.1f\n", stat, maxStat);
    }

    public boolean CheckIfDead() {
        return this.health + this.armour == 0 ? true : false;
    }

    /*TODO
     * ChooseEnemyManually(), ChooseEnemyAutomatically()
     * Make that way, so Attack method returned the way an enemy was attacked, e.g Critical Hit, Parried/Dodged, Hit,
     * Annihilated, Healed, Star Dashed
     * 
     */
    public static Unit ChooseEnemyManually(String WhichPlayerAttacks) {
        Scanner in = new Scanner(System.in);
        Unit enemyToAttack = null;
        if (WhichPlayerAttacks == "first") {
            for (int i = 0; i < 3; i++) {
                System.out.println(" " + (i + 1) + ". " + Initialization.secondPlayerWarriors[i].getClass().toString());
            }
            System.out.println("Pick which enemy you want to attack (enter number)");
            System.out.print("> ");
            String enemyNumber = in.next();
            while (!enemyNumber.equals("1") && !enemyNumber.equals("2") && !enemyNumber.equals("3")) {
                System.out.println("Wrong enemy's number, try again.");
                System.out.print("> ");
                enemyNumber = in.next();
            }
            int intEnemyNumber = Integer.parseInt(enemyNumber);
            enemyToAttack = Initialization.secondPlayerWarriors[intEnemyNumber - 1];
            System.out.println("\033[H\033[2J");
        } else if (WhichPlayerAttacks == "second") {
            for (int i = 0; i < 3; i++) {
                System.out.println(" " + (i + 1) + " " + Initialization.firstPlayerWarriors[i].getClass().toString());
            }
            System.out.println("Pick which enemy you want to attack (enter number)");
            System.out.print("> ");
            String enemyNumber = in.next();
            while (!enemyNumber.equals("1") && !enemyNumber.equals("2") && !enemyNumber.equals("3")) {
                System.out.println("Wrong enemy's number, try again.");
                System.out.print("> ");
                enemyNumber = in.next();
            }
            int intEnemyNumber = Integer.parseInt(enemyNumber);
            enemyToAttack = Initialization.firstPlayerWarriors[intEnemyNumber - 1];
            System.out.println("\033[H\033[2J");
        }
        return enemyToAttack;
    }

    public static void PrintCommonCharacteristics(Unit warrior) {
        if (warrior.getClass().equals(Knight.class)) {
            System.out.print("Knight\n Health: ");
            ShowBar(warrior.health, Knight.MAX_HEALTH, Colors.ANSI_RED);
            System.out.print(" Armour: ");
            ShowBar(warrior.armour, Knight.MAX_ARMOUR, Colors.ANSI_BLUE);
        } else if (warrior.getClass().equals(Wizard.class)) {
            System.out.print("Wizard\n Health: ");
            ShowBar(warrior.health, Wizard.MAX_HEALTH, Colors.ANSI_RED);
            System.out.print(" Mana:   ");
            Wizard warriorWizard = (Wizard) warrior;
            ShowBar(warriorWizard.mana, Wizard.MAX_MANA, Colors.ANSI_PURPLE);
        } else if (warrior.getClass().equals(Terminator.class)) {
            System.out.print("Terminator\n Health:  ");
            ShowBar(warrior.health, Terminator.MAX_HEALTH, Colors.ANSI_RED);
            System.out.print(" Armour:  ");
            ShowBar(warrior.armour, Terminator.MAX_ARMOUR, Colors.ANSI_BLUE);
            Terminator warriorTerminator = (Terminator) warrior;
            System.out.print(" Ability: ");
            ShowBar(warriorTerminator.AnnihialationAbility, Terminator.ABILITY_CHARGED, Colors.ANSI_GREEN);
        }
    }

    public double GetCriticalChance() {
        double criticalChance = 1;
        if (this.health > 75) {
            Random random = new Random();
            criticalChance = 1 + random.nextInt(50)/100.0;
        }
        return criticalChance;
    }
    /*TODO ClassAttack() */
    public void Attack(Unit attacked) {
        Random random = new Random();
        double critChance = this.GetCriticalChance();
        if (attacked.getClass().equals(Knight.class)){
            Knight attackedKnight = (Knight) attacked;
            attacked.chanceToParry = attackedKnight.GetChanceToParry();
            if (attacked.chanceToParry < random.nextInt(75)/100.0) {
                if (attacked.armour - this.attack*critChance > 0) {
                    attacked.armour -= this.attack*GetCriticalChance();
                    System.out.println("Knight got hit");
                } else {
                    if (attacked.health - (this.attack*critChance - attacked.armour) > 0) {
                        attacked.health -= (this.attack*critChance - attacked.armour);
                        attacked.armour = 0;
                        System.out.println("Knight got hit");
                    } else {
                        attacked.health = 0;
                        System.out.println("Knight recieved a fatal blow");
                    }
                }
            } else {
                System.out.println("DODGED");
            }
        } else if (attacked.getClass().equals(Wizard.class)) {
            if (attacked.armour - this.attack*critChance > 0) {
                attacked.armour -= this.attack*critChance;
                System.out.println("Wizard got hit");
            } else {
                if (attacked.health - (this.attack*critChance - attacked.armour) > 0) {
                    attacked.health -= (this.attack*critChance - attacked.armour);
                    attacked.armour = 0;
                    System.out.println("Wizard got hit");
                } else {
                    attacked.health = 0;
                    System.out.println("Wizard recieved a fatal blow");
                }
            }
        } else if (attacked.getClass().equals(Terminator.class)){
            if (attacked.armour - this.attack*critChance > 0) {
                attacked.armour -= this.attack*critChance;
                System.out.println("Terminator got hit");
            } else {
                if (attacked.health - (this.attack*critChance - attacked.armour) > 0) {
                    attacked.health -= (this.attack*critChance - attacked.armour);
                    attacked.armour = 0;
                    System.out.println("Terminator got hit");
                } else {
                    attacked.health = 0;
                    System.out.println("Terminator recieved a fatal blow");
                }
            }
        }
    }
     

}

class Knight extends Unit {
    final static double MAX_HEALTH = 100, MAX_ARMOUR = 50, MAX_ATTACK = 25;
    double chanceToParry;
    Knight(double armour, double health, double attack, double chanceToParry) {
        super(armour, health, attack);
        this.chanceToParry = chanceToParry;
    }
    public double GetChanceToParry() {
        double chanceToParry = 0;
        if (this.health < 50) {
            Random random = new Random();
            chanceToParry = random.nextInt(100)/100.0;
        }
        return chanceToParry;
    }
}

class Wizard extends Unit {
    final static double MAX_HEALTH = 100, MAX_ARMOUR = 0, MAX_ATTACK = 45, MAX_MANA = 50, MANA_REGENERATION = 5;
    double mana;
    Wizard(double armour, double health, double attack, double mana) {
        super(armour, health, attack);
        this.mana = mana;
    }
}

class Terminator extends Unit {
    final static double MAX_HEALTH = 100, MAX_ARMOUR = 100, MAX_ATTACK = 15, ABILITY_CHARGED = 25;
    double AnnihialationAbility;
    Terminator(double armour, double health, double attack, double AnnihialationAbility) {
        super(armour, health, attack);
        this.AnnihialationAbility = AnnihialationAbility;
    }
}