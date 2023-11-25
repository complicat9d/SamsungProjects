import java.util.Comparator;

public class StringComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        int i1 = Integer.parseInt(o1);
        int i2 = Integer.parseInt(o2);
        if (i1 > i2) {
            return 1;
        } else if (i1 < i2) {
            return -1;
        } else {
            return 0;
        }
    }
}
