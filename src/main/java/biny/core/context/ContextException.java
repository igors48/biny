package biny.core.context;

import static biny.core.context.Reflector.getClassName;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 15.01.13
 */
public final class ContextException extends Exception {

    public static ContextException canNotCreateClassDescriptor(final Class clazz, final Throwable cause) {
        return new ContextException(String.format("Can not create descriptor for class [ %s ]", getClassName(clazz)), cause);
    }

    public static ContextException canNotFindDescriptorForClass(final Class clazz) {
        return new ContextException(String.format("Can not find descriptor for class [ %s ]", getClassName(clazz)));
    }

    public static ContextException canNotFindDescriptorForClassWithId(final int identifier) {
        return new ContextException(String.format("Can not find descriptor for class with id [ %d ]", identifier));
    }

    public static ContextException duplicateClassIdentifierFound(final int identifier) {
        return new ContextException(String.format("Duplicate class identifier [ %d ] found", identifier));
    }

    private ContextException(final String message) {
        super(message);
    }

    private ContextException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
