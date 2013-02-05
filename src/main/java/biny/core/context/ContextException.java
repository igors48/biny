package biny.core.context;

import static biny.core.context.Reflector.getClassName;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 15.01.13
 */
public class ContextException extends Exception {

    public static ContextException canNotCreateClassDescriptor(Class clazz, Throwable cause) {
        return new ContextException(String.format("Can not create descriptor for class [ %s ]", getClassName(clazz)), cause);
    }

    public static ContextException canNotFindDescriptorForClass(Class clazz) {
        return new ContextException(String.format("Can not find descriptor for class [ %s ]", getClassName(clazz)));
    }

    public static ContextException canNotFindDescriptorForClassWithId(int identifier) {
        return new ContextException(String.format("Can not find descriptor for class with id [ %d ]", identifier));
    }

    public static ContextException duplicateClassIdentifierFound(int identifier) {
        return new ContextException(String.format("Duplicate class identifier [ %d ] found", identifier));
    }

    private ContextException(String message) {
        super(message);
    }

    private ContextException(String message, Throwable cause) {
        super(message, cause);
    }

}
