package unit.writer;

import org.junit.Assert;
import org.junit.Test;
import unit.reflector.ObjectWithAggregate;
import unit.reflector.SimpleObject;
import unit.writer.adapter.LongPart;
import unit.writer.adapter.StringPart;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 07.01.13
 */
public class ObjectWithAggregateWritingTest extends ObjectWriterTestBase {

    public static final int FIRST_LONG_VALUE = 48;
    public static final int SECOND_LONG_VALUE = 49;
    public static final String TEXT_VALUE = "text";

    @Test
    public void valueStoredFirstAggregateSecond() throws Exception {
        SimpleObject aggregate = new SimpleObject(SECOND_LONG_VALUE, TEXT_VALUE);
        ObjectWithAggregate objectWithAggregate = new ObjectWithAggregate(FIRST_LONG_VALUE, aggregate);

        this.writer.write(objectWithAggregate);

        LongPart firstLongValue = (LongPart) this.objectWriterAdapter.parts.get(1);
        LongPart secondLongValue = (LongPart) this.objectWriterAdapter.parts.get(3);
        StringPart textValue = (StringPart) this.objectWriterAdapter.parts.get(4);

        Assert.assertEquals(FIRST_LONG_VALUE, firstLongValue.value);
        Assert.assertEquals(SECOND_LONG_VALUE, secondLongValue.value);
        Assert.assertEquals(TEXT_VALUE, textValue.value);
    }

}
