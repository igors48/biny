package unit.writer;

import biny.core.ObjectWriter;
import org.junit.Before;
import unit.writer.adapter.FakeObjectWriterAdapter;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 07.01.13
 */
public class ObjectWriterTestBase {

    protected ObjectWriter writer;
    protected FakeObjectWriterAdapter objectWriterAdapter;

    @Before
    public void before() {
        this.objectWriterAdapter = new FakeObjectWriterAdapter();
        this.writer = new ObjectWriter(objectWriterAdapter);
    }

}
