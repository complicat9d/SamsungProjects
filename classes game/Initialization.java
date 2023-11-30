import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Random;

public class Initialization {

    static String nameFirstPlayer, nameSecondPlayer;
    static Unit[] firstPlayerWarriors, secondPlayerWarriors;

    public static void PrintOutFile (String fileName) throws IOException {
        Path pathFile = Paths.get(fileName);
        Scanner fileStream = new Scanner(pathFile);
        while (fileStream.hasNextLine()) {
            String line = fileStream.nextLine();
            System.out.println(line);
        }
        fileStream.close();
    }

    public static void PrintOutMatrix(int [][]matrix) {

    }

    public static void Menu() {

    }

    public static void TwoPlayersWarriorsInitialization() {
        firstPlayerWarriors = new Unit[3];
        secondPlayerWarriors = new Unit[3];
        Scanner in = new Scanner(System.in);

        System.out.println("Let " + nameFirstPlayer + " choose their three warriors");
        System.out.println("List of classes:\n - Knight\n - Wizard\n - Terminator");
        for (int i = 0; i < 3; ++i) {
            String classWarrior;
            classWarrior = in.next();
            while (!classWarrior.equals("Knight") && !classWarrior.equals("Wizard") && !classWarrior.equals("Terminator")) {
                System.out.println("Wrong warrior's name, try again.");
                classWarrior = in.next();
            }
            if (classWarrior.equals("Knight")) {
                firstPlayerWarriors[i] = new Knight(Knight.MAX_ARMOUR, Knight.MAX_HEALTH, Knight.MAX_ATTACK, 0);
            } else if (classWarrior.equals("Wizard")) {
                firstPlayerWarriors[i] = new Wizard(Wizard.MAX_ARMOUR, Wizard.MAX_HEALTH, Wizard.MAX_ATTACK, Wizard.MAX_MANA);
            } else if (classWarrior.equals("Terminator")) {
                firstPlayerWarriors[i] = new Terminator(Terminator.MAX_ARMOUR, Terminator.MAX_HEALTH, Terminator.MAX_ATTACK, 0);
            }
        }

        System.out.println("Let " + nameSecondPlayer + " choose their three warriors");
        for (int i = 0; i < 3; ++i) {
            String classWarrior;
            classWarrior = in.next();
            while (!classWarrior.equals("Knight") && !classWarrior.equals("Wizard") && !classWarrior.equals("Terminator")) {
                System.out.println("Wrong warrior's name, try again.");
                classWarrior = in.next();
            }
            if (classWarrior.equals("Knight")) {
                secondPlayerWarriors[i] = new Knight(Knight.MAX_ARMOUR, Knight.MAX_HEALTH, Knight.MAX_ATTACK, 0);
            } else if (classWarrior.equals("Wizard")) {
                secondPlayerWarriors[i] = new Wizard(Wizard.MAX_ARMOUR, Wizard.MAX_HEALTH, Wizard.MAX_ATTACK, Wizard.MAX_MANA);
            } else if (classWarrior.equals("Terminator")) {
                secondPlayerWarriors[i] = new Terminator(Terminator.MAX_ARMOUR, Terminator.MAX_HEALTH, Terminator.MAX_ATTACK, 0);
            }
        }

    }

    public static void OnePlayerWarriorsInitialization() {
        firstPlayerWarriors = new Unit[3];
        secondPlayerWarriors = new Unit[3];
        Scanner in = new Scanner(System.in);

        System.out.println("Let " + nameFirstPlayer + " choose their three warriors");
        System.out.println("List of classes:\n - Knight\n - Wizard\n - Terminator");
        for (int i = 0; i < 3; ++i) {
            String classWarrior;
            classWarrior = in.next();
            
            while (!classWarrior.equals("Knight") && !classWarrior.equals("Wizard") && !classWarrior.equals("Terminator")) {
                System.out.println("Wrong warrior's name, try again.");
                classWarrior = in.next();
            }
            if (classWarrior.equals("Knight")) {
                firstPlayerWarriors[i] = new Knight(Knight.MAX_ARMOUR, Knight.MAX_HEALTH, Knight.MAX_ATTACK, 0);
            } else if (classWarrior.equals("Wizard")) {
                firstPlayerWarriors[i] = new Wizard(Wizard.MAX_ARMOUR, Wizard.MAX_HEALTH, Wizard.MAX_ATTACK, Wizard.MAX_MANA);
            } else if (classWarrior.equals("Terminator")) {
                firstPlayerWarriors[i] = new Terminator(Terminator.MAX_ARMOUR, Terminator.MAX_HEALTH, Terminator.MAX_ATTACK, 0);
            }
        }
        Random random = new Random();
        for (int i = 0; i < 3; ++i) {
            if (random.nextInt(3) == 0) {
                secondPlayerWarriors[i] = new Knight(Knight.MAX_ARMOUR, Knight.MAX_HEALTH, Knight.MAX_ATTACK, 0);
            } else if (random.nextInt(3) == 1) {
                secondPlayerWarriors[i] = new Wizard(Wizard.MAX_ARMOUR, Wizard.MAX_HEALTH, Wizard.MAX_ATTACK, Wizard.MAX_MANA);
            } else {
                secondPlayerWarriors[i] = new Terminator(Terminator.MAX_ARMOUR, Terminator.MAX_HEALTH, Terminator.MAX_ATTACK, 0);
            }
        }

    }

    public static void AutomaticInitialization() {
        firstPlayerWarriors = new Unit[3];
        secondPlayerWarriors = new Unit[3];
        Random random = new Random();
        for (int i = 0; i < 3; ++i) {
            if (random.nextInt(3) == 0) {
                firstPlayerWarriors[i] = new Knight(Knight.MAX_ARMOUR, Knight.MAX_HEALTH, Knight.MAX_ATTACK, 0);
            } else if (random.nextInt(3) == 1) {
                firstPlayerWarriors[i] = new Wizard(Wizard.MAX_ARMOUR, Wizard.MAX_HEALTH, Wizard.MAX_ATTACK, Wizard.MAX_MANA);
            } else {
                firstPlayerWarriors[i] = new Terminator(Terminator.MAX_ARMOUR, Terminator.MAX_HEALTH, Terminator.MAX_ATTACK, 0);
            }
        }
        for (int i = 0; i < 3; ++i) {
            if (random.nextInt(3) == 0) {
                secondPlayerWarriors[i] = new Knight(Knight.MAX_ARMOUR, Knight.MAX_HEALTH, Knight.MAX_ATTACK, 0);
            } else if (random.nextInt(3) == 1) {
                secondPlayerWarriors[i] = new Wizard(Wizard.MAX_ARMOUR, Wizard.MAX_HEALTH, Wizard.MAX_ATTACK, Wizard.MAX_MANA);
            } else {
                secondPlayerWarriors[i] = new Terminator(Terminator.MAX_ARMOUR, Terminator.MAX_HEALTH, Terminator.MAX_ATTACK, 0);
            }
        }
    }

    public static String GameMode() {
        //cannot close in stream, otherwise it will be closes in other functions
        Scanner in = new Scanner(System.in);
        System.out.println("Choose the game mode, you want to play.\n 1. Two players\n 2. Half-Automated\n 3. Fully-automated");
        String userInput = in.next();
        while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3")) {
            System.out.println("Wrong game mode, try again.");
            userInput = in.next();
        }

        return userInput;
    }

    public static void PlayersGreetings(String userInput) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        
        if (userInput.equals("1")) {
            System.out.println("Hello, stranger! How may I call you?");
            nameFirstPlayer = in.next();
            System.out.println("How shalth I call your opponent?");
            nameSecondPlayer = in.next();
            
        } else if (userInput.equals("2")) {
            System.out.println("Hello, stranger! How may I call you?");
            nameFirstPlayer = in.next();
            System.out.println("Well, let's see, whether you have enough might to smite me.");
            nameSecondPlayer = "BOT";
        } else {
            nameFirstPlayer = "BOT1";
            nameSecondPlayer = "BOT2";

            
            char[] loadingBar = new char[20];
            for (int i = 0; i < 20; ++i) {
                loadingBar[i] = '⬜';
                System.out.println("Initializing classes. LOADING...");
                System.out.print(5*(i + 1) + " /" + 100 + "  ");
                for (int j = 0; j < 20; ++j) {
                    System.out.print(loadingBar[j]);
                }
                Thread.sleep(500);
                System.out.println("\033[H\033[2J");    
            }
            System.err.println("SUCCESSFULLY LOADED");
            
        }

    }

    public static void GameInitialization() throws InterruptedException {
        String userInput = GameMode();
        PlayersGreetings(userInput);
        if (userInput.equals("1")) {
            TwoPlayersWarriorsInitialization();
        } else if (userInput.equals("2")) {
            OnePlayerWarriorsInitialization();
            
        } else {
            AutomaticInitialization();
        }
    }

}