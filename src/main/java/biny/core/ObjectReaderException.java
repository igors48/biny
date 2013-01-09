package biny.core;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 08.01.13
 */
public class ObjectReaderException extends Exception {

    public ObjectReaderException(Throwable cause) {
        super(cause);
    }

    public ObjectReaderException(String message) {
        super(message);
    }

    public static ObjectReaderException unknownIdentifier(int identifier) {
        return new ObjectReaderException(String.format("Unknown object identifier [ %d ]", identifier));
    }

    public static ObjectReaderException unexpectedType(Type type) {
        return new ObjectReaderException(String.format("Unexpected object type [ %s ]", type));
    }

}
