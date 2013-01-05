package biny.core;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 04.01.13
 */
public class ReflectorException extends Exception {

    private ReflectorException(String message) {
        super(message);
    }

    public static ReflectorException mustBeOnlyOneConstructor(Class clazz, int constructorCount) {
        return new ReflectorException(String.format("Class [ %s ] must have only one constructor. But [ %d ] constructor found.", clazz.getName(), constructorCount));
    }

    public static ReflectorException notAnnotatedConstructorParameterFound(Class clazz) {
        return new ReflectorException(String.format("Found not annotated parameter in class [ %s ] constructor", clazz.getName()));
    }

    public static ReflectorException correspondingFieldNotFound(Class clazz, String annotatedParameter) {
        return new ReflectorException(String.format("There is no corresponding field for annotated parameter [ %s ] in class [ %s ] constructor", annotatedParameter, clazz.getName()));
    }

}
