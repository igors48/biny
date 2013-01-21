package biny.core;

import static biny.core.Reflector.getClassName;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 04.01.13
 */
public class ReflectorException extends Exception {

    private ReflectorException(String message) {
        super(message);
    }

    public static ReflectorException mustBeOnlyOneConstructor(Class clazz, int constructorCount) {
        return new ReflectorException(String.format("Class [ %s ] must have only one constructor. But [ %d ] constructor found.", getClassName(clazz), constructorCount));
    }

    public static ReflectorException notAnnotatedConstructorParameterFound(Class clazz) {
        return new ReflectorException(String.format("Found not annotated parameter in class [ %s ] constructor", getClassName(clazz)));
    }

    public static ReflectorException correspondingFieldNotFound(Class clazz, String annotatedParameter) {
        return new ReflectorException(String.format("There is no corresponding field for annotated parameter [ %s ] in class [ %s ] constructor", annotatedParameter, getClassName(clazz)));
    }

    public static ReflectorException classIdentifierNotFound(Class clazz) {
        return new ReflectorException(String.format("There is no identifier in class [ %s ]", getClassName(clazz)));
    }

    public static ReflectorException wrongCountOfActualTypeArguments(Class clazz, int count) {
        return new ReflectorException(String.format("Wrong count of actual type arguments [ %d ] for list in class [ %s ]", count, getClassName(clazz)));
    }

    public static ReflectorException classIsNotAvailable(String className) {
        return new ReflectorException(String.format("Class [ %s ] is not available", className));
    }

    public static ReflectorException innerListsAreNotSupported() {
        return new ReflectorException("Inner lists are not supported");
    }

    public static ReflectorException notFieldAnnotatedParameterFound(Class clazz) {
        return new ReflectorException(String.format("Parameter without @Field annotation found in class [ %s ] constructor", getClassName(clazz)));
    }

}
