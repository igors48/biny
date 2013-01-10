package unit.reader;

import biny.core.*;
import org.junit.Assert;
import org.junit.Test;
import unit.reflector.ObjectWithAggregate;
import unit.reflector.ObjectWithList;
import unit.reflector.SimpleObject;
import unit.writer.adapter.FakeObjectReaderAdapter;
import unit.writer.adapter.FakeObjectWriterAdapter;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 07.01.13
 */
public class ObjectReaderTest {

    protected Context context;

    protected FakeObjectWriterAdapter writerAdapter;
    protected ObjectWriter writer;

    protected FakeObjectReaderAdapter readerAdapter;
    protected ObjectReader reader;

    @Test
    public void simpleObjectRoundTrip() throws ReflectorException, ObjectWriterException, ObjectReaderException {
        SimpleObject fixture = SimpleObject.SIMPLE_OBJECT;

        SimpleObject restored = (SimpleObject) writeThenRead(fixture);

        Assert.assertEquals(fixture, restored);
    }

    @Test
    public void objectWithAggregateRoundTrip() throws ReflectorException, ObjectWriterException, ObjectReaderException {
        ObjectWithAggregate fixture = ObjectWithAggregate.OBJECT_WITH_AGGREGATE;

        ObjectWithAggregate restored = (ObjectWithAggregate) writeThenRead(fixture);

        Assert.assertEquals(fixture, restored);
    }

    @Test
    public void objectWithListRoundTrip() throws ReflectorException, ObjectWriterException, ObjectReaderException {
        ObjectWithList fixture = ObjectWithList.OBJECT_WITH_LIST;

        ObjectWithList restored = (ObjectWithList) writeThenRead(fixture);

        Assert.assertEquals(fixture, restored);
    }

    private Object writeThenRead(Object fixture) throws ObjectWriterException, ReflectorException, ObjectReaderException {
        this.writerAdapter = new FakeObjectWriterAdapter();
        this.writer = new ObjectWriter(this.writerAdapter);

        this.writer.write(fixture);

        this.readerAdapter = new FakeObjectReaderAdapter(this.writerAdapter.parts);

        this.context = new Context(SimpleObject.class, ObjectWithAggregate.class, ObjectWithList.class);
        this.reader = new ObjectReader(this.context);

        return this.reader.read(this.readerAdapter);
    }

}
