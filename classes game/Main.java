public class Main {
    public static void main(String args[]) throws InterruptedException {
        Initialization.GameInitialization();
        for (int i = 0; i < 3; ++i) {
            Unit.PrintCommonCharacteristics(Initialization.firstPlayerWarriors[i]);
        }
        
    }
}
