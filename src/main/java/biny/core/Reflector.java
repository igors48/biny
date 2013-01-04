package biny.core;

import biny.core.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 04.01.13
 */
public class Reflector {

    public static List<Field> parse(Class clazz) throws ReflectorException {
        Assert.notNull(clazz);

        List<Field> fields = new ArrayList<Field>();

        Constructor constructor = getConstructor(clazz);

        Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
        List<Annotation> fieldAnnotations = new ArrayList<Annotation>();

        for (Annotation[] annotations : parameterAnnotations) {

            if (annotations.length == 0) {
                throw ReflectorException.notAnnotatedConstructorParameterFound(clazz);
            }

            Annotation fieldAnnotation = getFieldAnnotation(annotations);
            fieldAnnotations.add(fieldAnnotation);
        }

        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Field[] declaredFields = clazz.getDeclaredFields();

        return fields;
    }

    private static Annotation getFieldAnnotation(Annotation[] annotations) {

        for (Annotation annotation : annotations) {

            if (annotation.annotationType().equals(biny.core.Field.class)) {
                return annotation;
            }
        }

        return null;
    }

    private static Constructor getConstructor(Class clazz) throws ReflectorException {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        if (constructors.length != 1) {
            throw ReflectorException.mustBeOnlyOneConstructor(clazz, constructors.length);
        }

        return constructors[0];
    }

}
