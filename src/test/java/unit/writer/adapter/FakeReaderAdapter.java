package unit.writer.adapter;

import biny.core.reader.ReaderAdapter;
import biny.core.util.Assert;

import java.util.Iterator;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 07.01.13
 */
public class FakeReaderAdapter implements ReaderAdapter {

    private final Iterator<Part> parts;

    public FakeReaderAdapter(List<Part> parts) {
        Assert.notNull(parts);
        this.parts = parts.iterator();
    }

    @Override
    public int readAggregateIdentifier() {
        return ((IdentifierPart) next()).identifier;
    }

    @Override
    public long readLong() {
        return ((LongPart) next()).value;
    }

    @Override
    public String readString() {
        return ((StringPart) next()).value;
    }

    @Override
    public int readListLength() {
        return ((ListStartPart) next()).listSize;
    }

    private Part next() {
        return this.parts.next();
    }

}
