package biny.core.reader;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 08.01.13
 */
public class ObjectReaderException extends Exception {

    public ObjectReaderException(final Throwable cause) {
        super(cause);
    }

    private ObjectReaderException(final String message) {
        super(message);
    }

    public static ObjectReaderException canNotInstantiateObjectOfClass(final String className) {
        return new ObjectReaderException(String.format("Can not instantiate object of class [ %s ]", className));
    }

    public static ObjectReaderException canNotInstantiateObjectWithId(final int identifier) {
        return new ObjectReaderException(String.format("Can not instantiate object with id [ %d ]", identifier));
    }

    public static ObjectReaderException canNotInstantiateListElementOfType(final String type) {
        return new ObjectReaderException(String.format("Can not instantiate object of type [ %s ]", type));
    }

}
