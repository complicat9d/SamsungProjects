import java.util.Random;

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
            System.out.print("Terminator\n Health: ");
            ShowBar(warrior.health, Terminator.MAX_HEALTH, Colors.ANSI_RED);
            System.out.print(" Armour: ");
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

    public void Attack(Unit Attacked) {
        Random random = new Random();
        double critChance = this.GetCriticalChance();
        if (Attacked.getClass().equals(Knight.class)){
            if (Attacked.chanceToParry < random.nextInt(100)/100.0) {
                if (Attacked.armour - this.attack*critChance > 0) {
                    Attacked.armour -= this.attack*GetCriticalChance();
                } else {
                    Attacked.health -= (this.attack*critChance - Attacked.armour);
                    Attacked.armour = 0;
                }
            } 
        } else if (Attacked.getClass().equals(Wizard.class)) {
            if (Attacked.armour - this.attack*critChance > 0) {
                Attacked.armour -= this.attack*critChance;
            } else {
                Attacked.health -= (this.attack*critChance - Attacked.armour);
                Attacked.armour = 0;
            }
        } else if (Attacked.getClass().equals(Terminator.class)){
            if (Attacked.armour - this.attack*critChance > 0) {
                Attacked.armour -= this.attack*critChance;
            } else {
                Attacked.health -= (this.attack*critChance - Attacked.armour);
                Attacked.armour = 0;
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
            chanceToParry = random.nextInt(75)/100;
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