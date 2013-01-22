package unit.reflector.validation;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 04.01.13
 */
public class TwoConstructor {

    public final long first;
    public final String second;

    public TwoConstructor(long first, String second) {
        this.first = first;
        this.second = second;
    }

    public TwoConstructor(String second, long first) {
        this.second = second;
        this.first = first;
    }

}
