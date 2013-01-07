package unit.writer;

import biny.core.ObjectWriterException;
import org.junit.Assert;
import org.junit.Test;
import unit.reflector.SimpleObject;
import unit.writer.adapter.IdentifierPart;
import unit.writer.adapter.LongPart;
import unit.writer.adapter.StringPart;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public class SimpleObjectWriteTest extends ObjectWriterTestBase {

    private static final int LONG_VALUE = 48;
    private static final String STRING_VALUE = "text";
    private static final SimpleObject SIMPLE_OBJECT = new SimpleObject(LONG_VALUE, STRING_VALUE);

    @Test
    public void objectIdentifierStoredFirst() throws ObjectWriterException {
        this.writer.write(SIMPLE_OBJECT);

        IdentifierPart storedIdentifier = (IdentifierPart) this.objectWriterAdapter.parts.get(0);

        Assert.assertEquals(SimpleObject.IDENTIFIER, storedIdentifier.identifier);
    }

    @Test
    public void objectFieldsStoredInCorrectOrder() throws Exception {
        this.writer.write(SIMPLE_OBJECT);

        LongPart longPart = (LongPart) this.objectWriterAdapter.parts.get(1);
        StringPart stringPart = (StringPart) this.objectWriterAdapter.parts.get(2);

        Assert.assertEquals(LONG_VALUE, longPart.value);
        Assert.assertEquals(STRING_VALUE, stringPart.value);
    }

}
