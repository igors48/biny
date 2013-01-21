package biny.core.reader;

import biny.core.ReaderAdapter;
import biny.core.meta.AbstractField;
import biny.core.meta.LongField;
import biny.core.util.Assert;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 17.01.13
 */
public class LongFieldReader implements FieldReader {

    public static final LongFieldReader LONG_FIELD_READER = new LongFieldReader();

    @Override
    public Object read(AbstractField field, ReaderAdapter adapter) throws FieldReaderException {
        Assert.notNull(field);
        Assert.isTrue(field instanceof LongField, "");
        Assert.notNull(adapter);

        return adapter.readLong();
    }

}
