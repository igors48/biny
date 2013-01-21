package unit.writer;

import biny.core.ObjectWriter;
import biny.core.context.Context;
import biny.core.context.ContextException;
import org.junit.Before;
import unit.reflector.ObjectWithAggregate;
import unit.reflector.ObjectWithList;
import unit.reflector.SimpleObject;
import unit.writer.adapter.FakeWriterAdapter;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 07.01.13
 */
public class ObjectWriterTestBase {

    protected Context context;
    protected ObjectWriter writer;
    protected FakeWriterAdapter objectWriterAdapter;

    @Before
    public void before() throws ContextException {
        this.context = new Context(SimpleObject.class, ObjectWithAggregate.class, ObjectWithList.class);
        this.objectWriterAdapter = new FakeWriterAdapter();
        this.writer = new ObjectWriter(this.context);
    }

}
