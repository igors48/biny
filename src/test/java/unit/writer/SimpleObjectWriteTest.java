package unit.writer;

import biny.core.ObjectWriter;
import biny.core.ObjectWriterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unit.reflector.SimpleObject;
import unit.writer.adapter.FakeObjectWriterAdapter;
import unit.writer.adapter.IdentifierPart;
import unit.writer.adapter.LongPart;
import unit.writer.adapter.StringPart;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public class SimpleObjectWriteTest {

    private static final int LONG_VALUE = 48;
    private static final String STRING_VALUE = "text";
    private static final SimpleObject SIMPLE_OBJECT = new SimpleObject(LONG_VALUE, STRING_VALUE);

    protected ObjectWriter writer;
    protected FakeObjectWriterAdapter objectWriterAdapter;

    @Before
    public void before() {
        this.objectWriterAdapter = new FakeObjectWriterAdapter();
        this.writer = new ObjectWriter(objectWriterAdapter);
    }

    @Test
    public void objectIdentifierStoredFirst() throws ObjectWriterException {
        writer.write(SIMPLE_OBJECT);

        IdentifierPart storedIdentifier = (IdentifierPart) objectWriterAdapter.parts.get(0);

        Assert.assertEquals(SimpleObject.IDENTIFIER, storedIdentifier.identifier);
    }

    @Test
    public void objectFieldsStoredInCorrectOrder() throws Exception {
        writer.write(SIMPLE_OBJECT);

        LongPart longPart = (LongPart) objectWriterAdapter.parts.get(1);
        StringPart stringPart = (StringPart) objectWriterAdapter.parts.get(2);

        Assert.assertEquals(LONG_VALUE, longPart.value);
        Assert.assertEquals(STRING_VALUE, stringPart.value);
    }

}
