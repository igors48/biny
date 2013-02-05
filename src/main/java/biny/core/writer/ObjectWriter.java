package biny.core.writer;

import biny.core.context.ClassDescriptor;
import biny.core.context.Context;
import biny.core.context.ContextException;
import biny.core.context.Reflector;
import biny.core.metadata.AbstractField;
import biny.core.metadata.Type;
import biny.core.util.Assert;

import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public class ObjectWriter {

    private final Context context;

    public ObjectWriter(Context context) {
        Assert.notNull(context);
        this.context = context;
    }

    public void write(Object object, WriterAdapter writer) throws ObjectWriterException {
        Assert.notNull(object);
        Assert.notNull(writer);

        try {
            writeAggregate(object, writer);
        } catch (IllegalAccessException e) {
            throw new ObjectWriterException(e);
        } catch (ContextException e) {
            throw new ObjectWriterException(e);
        } catch (WriterAdapterException e) {
            throw new ObjectWriterException(e);
        }
    }

    private void writeAggregate(Object aggregate, WriterAdapter writer) throws IllegalAccessException, ContextException, WriterAdapterException {
        ClassDescriptor descriptor = this.context.getClassDescriptor(aggregate.getClass());
        writer.writeAggregateIdentifier(descriptor.identifier);

        for (AbstractField field : descriptor.fields) {
            Object object = field.field.get(aggregate);
            writeObject(object, writer);
        }

    }

    private void writeList(List list, WriterAdapter writer) throws IllegalAccessException, ContextException, WriterAdapterException {
        writer.writeListSize(list.size());

        for (Object item : list) {
            writeObject(item, writer);
        }
    }

    private void writeObject(Object object, WriterAdapter writer) throws IllegalAccessException, ContextException, WriterAdapterException {
        Type type = Reflector.getFieldType(object.getClass());

        switch (type) {
            case LONG:
                writer.writeLong((Long) object);
                break;
            case STRING:
                writer.writeString((String) object);
                break;
            case AGGREGATE:
                writeAggregate(object, writer);
                break;
            case LIST:
                writeList((List) object, writer);
                break;
        }
    }

}
