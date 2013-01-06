package biny.core;

import biny.core.util.Assert;

import java.lang.reflect.Field;

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
            Type type = getFieldType(field.getType());
            Object object = field.get(aggregate);

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
                    break;
            }
        }
    }

    public static Type getFieldType(Class clazz) {
        Assert.notNull(clazz);

        if (clazz.getName().equalsIgnoreCase("long")) {
            return Type.LONG;
        } else if (clazz == String.class) {
            return Type.STRING;
        } else {
            return Type.AGGREGATE;
        }
    }

}
