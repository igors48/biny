package biny.core.reader;

import biny.core.context.ClassDescriptor;
import biny.core.context.Context;
import biny.core.context.ContextException;
import biny.core.metadata.AbstractField;
import biny.core.metadata.ListField;
import biny.core.metadata.Type;
import biny.core.util.Assert;

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

    public Object read(ReaderAdapter adapter) throws ObjectReaderException {
        Assert.notNull(adapter);

        try {
            return readAggregate(adapter);
        } catch (ReaderAdapterException e) {
            throw new ObjectReaderException(e);
        } catch (ContextException e) {
            throw new ObjectReaderException(e);
        }
    }

    private Object readAggregate(ReaderAdapter adapter) throws ObjectReaderException, ReaderAdapterException, ContextException {
        int identifier = adapter.readAggregateIdentifier();

        ClassDescriptor descriptor = this.context.getClassDescriptor(identifier);

        List<Object> parameters = readConstructorParameters(adapter, descriptor);

        return callAggregateConstructor(descriptor, parameters);
    }

    private Object callAggregateConstructor(ClassDescriptor descriptor, List<Object> parameters) throws ObjectReaderException {

        try {
            return descriptor.constructor.newInstance(parameters.toArray());
        } catch (InvocationTargetException e) {
            throw ObjectReaderException.canNotInstantiateObjectOfClass(descriptor.name);
        } catch (InstantiationException e) {
            throw ObjectReaderException.canNotInstantiateObjectOfClass(descriptor.name);
        } catch (IllegalAccessException e) {
            throw ObjectReaderException.canNotInstantiateObjectOfClass(descriptor.name);
        }
    }

    private List<Object> readConstructorParameters(ReaderAdapter adapter, ClassDescriptor descriptor) throws ObjectReaderException, ContextException, ReaderAdapterException {
        List<Object> parameters = new ArrayList<Object>();

        for (AbstractField current : descriptor.fields) {
            Object parameter = null;

            switch (current.type) {
                case LONG:
                    parameter = adapter.readLong();
                    break;
                case STRING:
                    parameter = adapter.readString();
                    break;
                case AGGREGATE:
                    parameter = readAggregate(adapter);
                    break;
                case LIST:
                    parameter = readList(adapter, (ListField) current);
                    break;
            }

            parameters.add(parameter);
        }

        return parameters;
    }

    @SuppressWarnings(value = "unchecked")
    private Object readList(ReaderAdapter adapter, ListField listField) throws ObjectReaderException, ContextException, ReaderAdapterException {
        int listLength = adapter.readListLength();

        List list = new ArrayList(listLength);

        for (int index = 0; index < listLength; ++index) {
            Type elementType = listField.element.type;

            Object element = null;

            switch (elementType) {
                case LONG:
                    element = adapter.readLong();
                    break;
                case STRING:
                    element = adapter.readString();
                    break;
                case AGGREGATE:
                    element = readAggregate(adapter);
                    break;
                case LIST:
                    break;
            }

            if (element == null) {
                throw ObjectReaderException.canNotInstantiateListElementOfType(elementType.toString());
            }

            list.add(element);
        }

        return list;
    }

}
