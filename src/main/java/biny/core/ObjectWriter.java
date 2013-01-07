package biny.core;

import biny.core.util.Assert;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public class ObjectWriter {

    private final ObjectWriterAdapter writer;

    public ObjectWriter(ObjectWriterAdapter writer) {
        Assert.notNull(writer);
        this.writer = writer;
    }

    public void write(Object object) throws ObjectWriterException {
        Assert.notNull(object);

        try {
            writeAggregate(object);
        } catch (ReflectorException e) {
            throw new ObjectWriterException(e);
        } catch (IllegalAccessException e) {
            throw new ObjectWriterException(e);
        }
    }

    private void writeAggregate(Object aggregate) throws ReflectorException, IllegalAccessException {
        ClassMetaData classMetaData = Reflector.createClassMetaData(aggregate.getClass());
        this.writer.writeAggregateIdentifier(classMetaData.identifier);

        for (Field field : classMetaData.fields) {
            Object object = field.get(aggregate);
            writeObject(object);
        }
    }

    private void writeList(List list) throws IllegalAccessException, ReflectorException {
        this.writer.writeListStart(list.size());

        for (Object item : list) {
            writeObject(item);
        }
    }

    private void writeObject(Object object) throws ReflectorException, IllegalAccessException {
        Type type = getObjectType(object.getClass());

        switch (type) {
            case LONG:
                this.writer.writeLong((Long) object);
                break;
            case STRING:
                this.writer.writeString((String) object);
                break;
            case AGGREGATE:
                writeAggregate(object);
                break;
            case LIST:
                writeList((List) object);
                break;
        }
    }

    public static Type getObjectType(Class clazz) {
        Assert.notNull(clazz);

        if (Long.class.isAssignableFrom(clazz)) {
            return Type.LONG;
        } else if (String.class.isAssignableFrom(clazz)) {
            return Type.STRING;
        } else if (List.class.isAssignableFrom(clazz)) {
            return Type.LIST;
        } else {
            return Type.AGGREGATE;
        }
    }

}
