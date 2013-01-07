package unit.writer;

import org.junit.Assert;
import org.junit.Test;
import unit.reflector.ObjectWithList;
import unit.writer.adapter.ListStartPart;
import unit.writer.adapter.LongPart;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 07.01.13
 */
public class ObjectWithListWriteTest extends ObjectWriterTestBase {

    private static final long FIRST_LONG = 1L;
    private static final long SECOND_LONG = 2L;
    private static final long THIRD_LONG = 3L;

    @Test
    public void listSizeStoredFirst() throws Exception {
        List<Long> list = createList();

        ObjectWithList objectWithList = new ObjectWithList(list);

        this.writer.write(objectWithList);

        ListStartPart listStartPart = (ListStartPart) this.objectWriterAdapter.parts.get(1);

        Assert.assertEquals(list.size(), listStartPart.listSize);
    }

    @Test
    public void listItemsStoredInCorrectOrder() throws Exception {
        List<Long> list = createList();

        ObjectWithList objectWithList = new ObjectWithList(list);

        this.writer.write(objectWithList);

        LongPart first = (LongPart) this.objectWriterAdapter.parts.get(2);
        Assert.assertEquals(FIRST_LONG, first.value);

        LongPart second = (LongPart) this.objectWriterAdapter.parts.get(3);
        Assert.assertEquals(SECOND_LONG, second.value);

        LongPart third = (LongPart) this.objectWriterAdapter.parts.get(4);
        Assert.assertEquals(THIRD_LONG, third.value);
    }

    private List<Long> createList() {
        List<Long> list = new ArrayList<Long>();

        list.add(FIRST_LONG);
        list.add(SECOND_LONG);
        list.add(THIRD_LONG);

        return list;
    }

}
