package biny.core;

import biny.core.annotation.Identifier;
import biny.core.meta.AbstractMetaData;
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

    public static Type getFieldType(Class clazz) {
        Assert.notNull(clazz);

        if (Long.class.isAssignableFrom(clazz) || clazz.getName().equals("long")) {
            return Type.LONG;
        } else if (String.class.isAssignableFrom(clazz)) {
            return Type.STRING;
        } else if (List.class.isAssignableFrom(clazz)) {
            return Type.LIST;
        } else {
            return Type.AGGREGATE;
        }
    }

    public static ClassMetaData createClassMetaData(Class clazz) throws ReflectorException {
        Assert.notNull(clazz);

        List<AbstractMetaData> fields = new ArrayList<AbstractMetaData>();

        Constructor constructor = findConstructor(clazz);
        List<biny.core.annotation.Field> fieldAnnotations = getFieldsAnnotations(constructor, clazz);

        Field[] declaredFields = clazz.getDeclaredFields();

        for (biny.core.annotation.Field fieldAnnotation : fieldAnnotations) {
            Field field = findCorrespondingField(fieldAnnotation, declaredFields, clazz);
            Type type = getFieldType(field.getType());
            fields.add(field);
        }

        int identifier = findClassIdentifier(clazz);

        return new ClassMetaData(identifier, clazz, fields);
    }

    public static Constructor findConstructor(Class clazz) throws ReflectorException {
        Assert.notNull(clazz);

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        if (constructors.length != 1) {
            throw ReflectorException.mustBeOnlyOneConstructor(clazz, constructors.length);
        }

        return constructors[0];
    }

    private static int findClassIdentifier(Class clazz) throws ReflectorException {
        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations) {

            if (annotation.annotationType().equals(Identifier.class)) {
                return ((Identifier) annotation).value();
            }
        }

        throw ReflectorException.classIdentifierNotFound(clazz);
    }

    private static Field findCorrespondingField(biny.core.annotation.Field fieldAnnotation, Field[] declaredFields, Class clazz) throws ReflectorException {
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

    private static List<biny.core.annotation.Field> getFieldsAnnotations(Constructor constructor, Class clazz) throws ReflectorException {
        Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
        List<biny.core.annotation.Field> fieldAnnotations = new ArrayList<biny.core.annotation.Field>();

        for (Annotation[] annotations : parameterAnnotations) {

            if (annotations.length == 0) {
                throw ReflectorException.notAnnotatedConstructorParameterFound(clazz);
            }

            Annotation fieldAnnotation = getFieldAnnotation(annotations);
            fieldAnnotations.add((biny.core.annotation.Field) fieldAnnotation);
        }

        return fieldAnnotations;
    }

    private static Annotation getFieldAnnotation(Annotation[] annotations) {

        for (Annotation annotation : annotations) {

            if (annotation.annotationType().equals(biny.core.annotation.Field.class)) {
                return annotation;
            }
        }

        //TODO consider throw an exception
        return null;
    }

}
