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

    public static List<Field> getFields(Class clazz) throws ReflectorException {
        Assert.notNull(clazz);

        List<Field> fields = new ArrayList<Field>();

        Constructor constructor = getConstructor(clazz);
        List<biny.core.Field> fieldAnnotations = getFieldsAnnotations(constructor, clazz);

        Field[] declaredFields = clazz.getDeclaredFields();

        for (biny.core.Field fieldAnnotation : fieldAnnotations) {
            Field field = findCorrespondingField(fieldAnnotation, declaredFields, clazz);
            fields.add(field);
        }

        return fields;
    }

    private static Field findCorrespondingField(biny.core.Field fieldAnnotation, Field[] declaredFields, Class clazz) throws ReflectorException {
        Field field = null;

        for (Field declaredField : declaredFields) {

            if (declaredField.getName().equals(fieldAnnotation.value())) {
                field = declaredField;
            }
        }

        if (field == null) {
            throw ReflectorException.correspondingFieldNotFound(clazz, fieldAnnotation.value());
        }

        return field;
    }

    private static List<biny.core.Field> getFieldsAnnotations(Constructor constructor, Class clazz) throws ReflectorException {
        Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
        List<biny.core.Field> fieldAnnotations = new ArrayList<biny.core.Field>();

        for (Annotation[] annotations : parameterAnnotations) {

            if (annotations.length == 0) {
                throw ReflectorException.notAnnotatedConstructorParameterFound(clazz);
            }

            Annotation fieldAnnotation = getFieldAnnotation(annotations);
            fieldAnnotations.add((biny.core.Field) fieldAnnotation);
        }
        return fieldAnnotations;
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
