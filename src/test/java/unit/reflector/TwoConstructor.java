package unit.reflector;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 04.01.13
 */
public class TwoConstructor {

    public final int first;
    public final String second;

    public TwoConstructor(int first, String second) {
        this.first = first;
        this.second = second;
    }

    public TwoConstructor(String second, int first) {
        this.second = second;
        this.first = first;
    }

}
