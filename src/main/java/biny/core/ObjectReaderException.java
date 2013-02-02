package biny.core;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 08.01.13
 */
public class ObjectReaderException extends Exception {

    public ObjectReaderException(Throwable cause) {
        super(cause);
    }

    private ObjectReaderException(String message) {
        super(message);
    }

    public static ObjectReaderException canNotInstantiateObjectOfClass(String className) {
        return new ObjectReaderException(String.format("Can not instantiate object of class [ %s ]", className));
    }

    public static ObjectReaderException canNotInstantiateObjectWithId(int identifier) {
        return new ObjectReaderException(String.format("Can not instantiate object with id [ %d ]", identifier));
    }

    public static ObjectReaderException canNotInstantiateListElementOfType(String type) {
        return new ObjectReaderException(String.format("Can not instantiate object of type [ %s ]", type));
    }

}
