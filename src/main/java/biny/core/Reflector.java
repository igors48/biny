package biny.core;

import biny.core.annotation.Identifier;
import biny.core.meta.*;
import biny.core.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 04.01.13
 */
public class Reflector {

    public static String getClassName(Class clazz) {
        Assert.notNull(clazz);

        return clazz.getCanonicalName();
    }

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

    public static ClassDescriptor createClassDescriptor(Class clazz, Set<String> availableClassNames) throws ReflectorException {
        Assert.notNull(clazz);
        Assert.notNull(availableClassNames);

        List<AbstractField> fields = new ArrayList<AbstractField>();

        Constructor constructor = findConstructor(clazz);
        List<biny.core.annotation.Field> fieldAnnotations = getFieldsAnnotations(constructor, clazz);

        Field[] declaredFields = clazz.getDeclaredFields();

        for (biny.core.annotation.Field fieldAnnotation : fieldAnnotations) {
            Field field = findCorrespondingField(fieldAnnotation, declaredFields, clazz);
            Class fieldClass = field.getType();

            AbstractField metaData = getFieldMetaData(clazz, field, fieldClass, availableClassNames);

            if (metaData != null) {
                fields.add(metaData);
            }
        }

        int identifier = findClassIdentifier(clazz);

        return new ClassDescriptor(identifier, getClassName(clazz), fields);
    }

    private static AbstractField getFieldMetaData(Class clazz, Field field, Class fieldClass, Set<String> availableClassNames) throws ReflectorException {
        Type type = getFieldType(fieldClass);

        AbstractField metaData = null;

        switch (type) {
            case LONG:
                metaData = new LongField(field);
                break;
            case STRING:
                metaData = new StringField(field);
                break;
            case AGGREGATE:
                metaData = createAggregateField(field, fieldClass, availableClassNames);
                break;
            case LIST:
                metaData = createListField(clazz, field, availableClassNames);
                break;
        }

        return metaData;
    }

    private static AbstractField createAggregateField(Field field, Class fieldClass, Set<String> availableClassNames) throws ReflectorException {
        String className = getClassName(fieldClass);
        assertClassAvailable(className, availableClassNames);

        return new AggregateField(className, field);
    }

    private static AbstractField createListField(Class clazz, Field field, Set<String> availableClassNames) throws ReflectorException {
        Class elementClass = getListElementClass(clazz, field);
        Type elementType = getFieldType(elementClass);

        AbstractListElement listElementMetaData = null;

        switch (elementType) {
            case LONG:
                listElementMetaData = new LongListElement();
                break;
            case STRING:
                listElementMetaData = new StringListElement();
                break;
            case AGGREGATE:
                String elementClassName = getClassName(elementClass);
                assertClassAvailable(elementClassName, availableClassNames);
                listElementMetaData = new AggregateListElement(elementClassName);
                break;
            case LIST:
                throw ReflectorException.innerListsAreNotSupported();
        }

        return new ListField(listElementMetaData, field);
    }

    private static Class getListElementClass(Class clazz, Field field) throws ReflectorException {
        java.lang.reflect.Type generic = field.getGenericType();
        ParameterizedType parameterizedType = (ParameterizedType) generic;
        java.lang.reflect.Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

        if (actualTypeArguments.length != 1) {
            throw ReflectorException.wrongCountOfActualTypeArguments(clazz, actualTypeArguments.length);
        }

        return (Class) actualTypeArguments[0];
    }

    private static void assertClassAvailable(String className, Set<String> availableClassNames) throws ReflectorException {

        if (!availableClassNames.contains(className)) {
            throw ReflectorException.classIsNotAvailable(className);
        }
    }

    public static Constructor findConstructor(Class clazz) throws ReflectorException {
        Assert.notNull(clazz);

        Constructor[] constructors = clazz.getDeclaredConstructors();

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

            if (fieldAnnotation == null) {
                throw ReflectorException.notFieldAnnotatedParameterFound(clazz);
            }

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

        return null;
    }

}
