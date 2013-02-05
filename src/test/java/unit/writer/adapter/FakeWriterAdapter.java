package unit.writer.adapter;

import biny.core.util.Assert;
import biny.core.writer.WriterAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public class FakeWriterAdapter implements WriterAdapter {

    public final List<Part> parts;

    public FakeWriterAdapter() {
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
    public void writeListSize(int listSize) {
        Assert.greaterOrEqual(listSize, 0, "");

        this.parts.add(new ListStartPart(listSize));
    }

}
