package unit.reader;

import biny.core.*;
import biny.core.context.Context;
import biny.core.context.ContextException;
import org.junit.Assert;
import org.junit.Test;
import unit.reflector.ObjectWithAggregate;
import unit.reflector.ObjectWithList;
import unit.reflector.SimpleObject;
import unit.writer.adapter.FakeReaderAdapter;
import unit.writer.adapter.FakeWriterAdapter;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 07.01.13
 */
public class ObjectReaderTest {

    protected Context context;

    protected FakeWriterAdapter writerAdapter;
    protected ObjectWriter writer;

    protected FakeReaderAdapter readerAdapter;
    protected ObjectReader reader;

    @Test
    public void simpleObjectRoundTrip() throws ReflectorException, ObjectWriterException, ObjectReaderException, ContextException {
        SimpleObject fixture = SimpleObject.SIMPLE_OBJECT;

        SimpleObject restored = (SimpleObject) writeThenRead(fixture);

        Assert.assertEquals(fixture, restored);
    }

    @Test
    public void objectWithAggregateRoundTrip() throws ReflectorException, ObjectWriterException, ObjectReaderException, ContextException {
        ObjectWithAggregate fixture = ObjectWithAggregate.OBJECT_WITH_AGGREGATE;

        ObjectWithAggregate restored = (ObjectWithAggregate) writeThenRead(fixture);

        Assert.assertEquals(fixture, restored);
    }

    @Test
    public void objectWithListRoundTrip() throws ReflectorException, ObjectWriterException, ObjectReaderException, ContextException {
        ObjectWithList fixture = ObjectWithList.OBJECT_WITH_LIST;

        ObjectWithList restored = (ObjectWithList) writeThenRead(fixture);

        Assert.assertEquals(fixture.list.size(), restored.list.size());

        for (int index = 0; index < fixture.list.size(); ++index) {
            Assert.assertEquals(fixture.list.get(index), restored.list.get(index));
        }
    }

    private Object writeThenRead(Object fixture) throws ObjectWriterException, ReflectorException, ObjectReaderException, ContextException {
        this.context = new Context(SimpleObject.class, ObjectWithAggregate.class, ObjectWithList.class);

        this.writerAdapter = new FakeWriterAdapter();
        this.writer = new ObjectWriter(this.context);

        this.writer.write(fixture, this.writerAdapter);

        this.readerAdapter = new FakeReaderAdapter(this.writerAdapter.parts);

        this.context = new Context(SimpleObject.class, ObjectWithAggregate.class, ObjectWithList.class);
        this.reader = new ObjectReader(this.context);

        return this.reader.read(this.readerAdapter);
    }

}
