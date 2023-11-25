import java.awt.*;
import java.util.Comparator;

public class capybara extends  Animal implements Comparable<capybara> {
    int cuteness;
    Color color;
    String name;

    public capybara(int cuteness, Color color, String name) {
        this.cuteness = cuteness;
        this.color = color;
        this.name = name;
    }

    @Override
    public int compareTo(capybara o) {
        if (this.cuteness > this.cuteness) {
            return 1;
        } else if (this.cuteness < this.cuteness) {
            return -1;
        }
        int sum1 = this.color.getBlue() + this.color.getGreen() + this.color.getRed();
        int sum2 = o.color.getBlue() + o.color.getGreen() + o.color.getRed();
        if (sum1 > sum2) {
            return 1;
        } else if (sum1 < sum2){
            return -1;
        }
        return this.name.compareTo(o.name);
    }

}
