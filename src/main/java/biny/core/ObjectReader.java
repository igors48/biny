package biny.core;

import biny.core.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
            Class clazz = field.getType().getClass();
            Object object = readObject(clazz, adapter);

            parameters.add(object);
        }

        return ObjectFactory.create(classMetaData.clazz, parameters);
    }

    private Object readObject(Class clazz, ObjectReaderAdapter adapter) throws ObjectReaderException, InvocationTargetException, InstantiationException, ReflectorException, IllegalAccessException {
        Type type = ObjectWriter.getObjectType(clazz);

        switch (type) {
            case LONG:
                return adapter.readLong();
            case STRING:
                return adapter.readString();
            case AGGREGATE:
                return readAggregate(adapter);
            case LIST:
                return readList(adapter);
        }

        throw ObjectReaderException.unexpectedType(type);
    }

    private List readList(ObjectReaderAdapter adapter) {
        return null;
    }

}
