package unit.reader;

import biny.core.*;
import org.junit.Assert;
import org.junit.Test;
import unit.reflector.SimpleObject;
import unit.writer.adapter.FakeObjectReaderAdapter;
import unit.writer.adapter.FakeObjectWriterAdapter;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 07.01.13
 */
public class SimpleObjectReadTest {

    @Test
    public void readSimpleObject() throws ReflectorException, ObjectWriterException, ObjectReaderException {
        FakeObjectWriterAdapter writerAdapter = new FakeObjectWriterAdapter();
        ObjectWriter writer = new ObjectWriter(writerAdapter);

        writer.write(SimpleObject.SIMPLE_OBJECT);

        FakeObjectReaderAdapter readerAdapter = new FakeObjectReaderAdapter(writerAdapter.parts);

        Context context = new Context(SimpleObject.class);
        ObjectReader reader = new ObjectReader(context);

        SimpleObject simpleObject = (SimpleObject) reader.read(readerAdapter);

        Assert.assertEquals(SimpleObject.SIMPLE_OBJECT, simpleObject);
    }

}
