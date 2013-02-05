package biny.core.context;

import static biny.core.context.Reflector.getClassName;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 04.01.13
 */
public final class ReflectorException extends Exception {

    private ReflectorException(final String message) {
        super(message);
    }

    public static ReflectorException mustBeOnlyOneConstructor(final Class clazz, final int constructorCount) {
        return new ReflectorException(String.format("Class [ %s ] must have only one constructor. But [ %d ] constructor found.", getClassName(clazz), constructorCount));
    }

    public static ReflectorException notAnnotatedConstructorParameterFound(final Class clazz) {
        return new ReflectorException(String.format("Found not annotated parameter in class [ %s ] constructor", getClassName(clazz)));
    }

    public static ReflectorException correspondingFieldNotFound(final Class clazz, final String annotatedParameter) {
        return new ReflectorException(String.format("There is no corresponding field for annotated parameter [ %s ] in class [ %s ] constructor", annotatedParameter, getClassName(clazz)));
    }

    public static ReflectorException classIdentifierNotFound(final Class clazz) {
        return new ReflectorException(String.format("There is no identifier in class [ %s ]", getClassName(clazz)));
    }

    public static ReflectorException wrongCountOfActualTypeArguments(final Class clazz, final int count) {
        return new ReflectorException(String.format("Wrong count of actual type arguments [ %d ] for list in class [ %s ]", count, getClassName(clazz)));
    }

    public static ReflectorException classIsNotAvailable(final String className) {
        return new ReflectorException(String.format("Class [ %s ] is not available in context", className));
    }

    public static ReflectorException innerListsAreNotSupported() {
        return new ReflectorException("Inner lists are not supported");
    }

    public static ReflectorException notFieldAnnotatedParameterFound(final Class clazz) {
        return new ReflectorException(String.format("Parameter without @Field annotation found in class [ %s ] constructor", getClassName(clazz)));
    }

}
