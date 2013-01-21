package biny.core.writer;

import biny.core.WriterAdapter;
import biny.core.meta.AbstractField;
import biny.core.meta.LongField;
import biny.core.util.Assert;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 17.01.13
 */
public class LongFieldWriter implements FieldWriter {

    @Override
    public void write(Object object, AbstractField field, WriterAdapter adapter) throws FieldWriterException {
        Assert.notNull(object);
        Assert.notNull(field);
        Assert.isTrue(field instanceof LongField, "");
        Assert.notNull(adapter);

        try {
            long value = field.field.getLong(object);
            adapter.writeLong(value);
        } catch (IllegalAccessException e) {
            throw new FieldWriterException(e);
        }
    }

}
