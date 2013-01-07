package unit.writer.adapter;

import biny.core.ObjectWriterAdapter;
import biny.core.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public class FakeObjectWriterAdapter implements ObjectWriterAdapter {

    public final List<Part> parts;

    public FakeObjectWriterAdapter() {
        this.parts = new ArrayList<Part>();
    }

    @Override
    public void writeAggregateIdentifier(int identifier) {
        Assert.greater(identifier, 0, "");

        this.parts.add(new IdentifierPart(identifier));
    }

    @Override
    public void writeLong(long value) {
        this.parts.add(new LongPart(value));
    }

    @Override
    public void writeString(String value) {
        Assert.notNull(value);

        this.parts.add(new StringPart(value));
    }

    @Override
    public void writeListStart(int listSize) {
        Assert.greaterOrEqual(listSize, 0, "");

        this.parts.add(new ListStartPart(listSize));
    }

}
