import java.awt.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        String[] array = new String[]{"2", "4", "1", "0", "10"};
//        Arrays.sort(array, new StringComparator());
//        System.out.println(Arrays.toString(array));

        capybara[] capybaras = new capybara[]{new capybara(100,  Color.pink, "Jenya"), new capybara(101, Color.black, "Evegniy")};

//        Animal animal = new Animal();
//        capybara capybara = new capybara(123, Color.CYAN, "sad");
//        LittleCapybara littleCapybara = new LittleCapybara(123, Color.PINK, "happy");
//
//        Animal animal2 = littleCapybara;
//        Animal[] animals = new Animal[]{littleCapybara, capybara};
//        System.out.println(animal2);
//        if (animals[1] instanceof LittleCapybara) {
//            LittleCapybara lc = (LittleCapybara)(animals[0]);
//            System.out.println(lc);
//        }
        GeometryObjects[] geometryObjects = new GeometryObjects[]{new Triangle(3.0, 4.0, 5.0), new Rectangle(1.0, 2.6), new Circle(5.7)};
        Arrays.sort(geometryObjects);
        System.out.println(Arrays.toString(geometryObjects));
        geometryObjects[0].setScale(100);
        Arrays.sort(geometryObjects);
        System.out.println(Arrays.toString(geometryObjects));
    }
}