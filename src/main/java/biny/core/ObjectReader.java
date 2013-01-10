package biny.core;

import biny.core.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 07.01.13
 */
public class ObjectReader {

    private final Context context;

    public ObjectReader(Context context) {
        Assert.notNull(context);
        this.context = context;
    }

    public Object read(ObjectReaderAdapter adapter) throws ObjectReaderException {
        Assert.notNull(adapter);

        try {
            return readAggregate(adapter);
        } catch (ReflectorException e) {
            throw new ObjectReaderException(e);
        } catch (IllegalAccessException e) {
            throw new ObjectReaderException(e);
        } catch (InvocationTargetException e) {
            throw new ObjectReaderException(e);
        } catch (InstantiationException e) {
            throw new ObjectReaderException(e);
        }
    }

    private Object readAggregate(ObjectReaderAdapter adapter) throws ObjectReaderException, ReflectorException, IllegalAccessException, InvocationTargetException, InstantiationException {
        int identifier = adapter.readAggregateIdentifier();
        ClassMetaData classMetaData = this.context.getClassMetaData(identifier);

        if (classMetaData == null) {
            throw ObjectReaderException.unknownIdentifier(identifier);
        }

        List<Object> parameters = new ArrayList<Object>();

        for (Field field : classMetaData.fields) {
            Class clazz = field.getType();
            Type type = ObjectWriter.getObjectType(clazz);

            Object object;
            if (type == Type.LIST) {
                //TODO this should be stored in class metadata when context is creating
                java.lang.reflect.Type generic = field.getGenericType();
                ParameterizedType parameterizedType = (ParameterizedType) generic;
                System.out.println("raw type: " + parameterizedType.getRawType());
                System.out.println("owner type: " + parameterizedType.getOwnerType());
                System.out.println("actual type args:");

                for (java.lang.reflect.Type t : parameterizedType.getActualTypeArguments()) {
                    System.out.println("    " + t);
                }

                object = null;
            } else {
                object = readObject(type, adapter);
            }

            parameters.add(object);
        }

        return ObjectFactory.create(classMetaData.clazz, parameters);
    }

    private Object readObject(Type type, ObjectReaderAdapter adapter) throws ObjectReaderException, InvocationTargetException, InstantiationException, ReflectorException, IllegalAccessException {

        switch (type) {
            case LONG:
                return adapter.readLong();
            case STRING:
                return adapter.readString();
            case AGGREGATE:
                return readAggregate(adapter);
        }

        throw ObjectReaderException.unexpectedType(type);
    }

    private List readList(Class element, ObjectReaderAdapter adapter) throws InvocationTargetException, InstantiationException, ObjectReaderException, ReflectorException, IllegalAccessException {
        long count = adapter.readListStart();

        List list = new ArrayList();

        for (int index = 0; index < count; ++index) {
            Object item = null;//readObject(element, adapter);

            list.add(item);
        }

        return list;
    }

}
