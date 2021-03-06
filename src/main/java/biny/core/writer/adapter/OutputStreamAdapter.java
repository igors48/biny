package biny.core.writer.adapter;

import biny.core.util.Assert;
import biny.core.writer.WriterAdapter;
import biny.core.writer.WriterAdapterException;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 02.02.13
 */
public class OutputStreamAdapter implements WriterAdapter {

    private final DataOutput dataOutput;

    public OutputStreamAdapter(final OutputStream outputStream) {
        Assert.notNull(outputStream);
        this.dataOutput = new DataOutputStream(outputStream);
    }

    @Override
    public final void writeAggregateIdentifier(final int identifier) throws WriterAdapterException {
        Assert.greaterOrEqual(identifier, 0, "");

        try {
            this.dataOutput.writeInt(identifier);
        } catch (IOException e) {
            throw new WriterAdapterException(e);
        }
    }

    @Override
    public final void writeLong(final long value) throws WriterAdapterException {

        try {
            this.dataOutput.writeLong(value);
        } catch (IOException e) {
            throw new WriterAdapterException(e);
        }
    }

    @Override
    public final void writeString(final String value) throws WriterAdapterException {
        Assert.isValidString(value);

        try {
            this.dataOutput.writeUTF(value);
        } catch (IOException e) {
            throw new WriterAdapterException(e);
        }
    }

    @Override
    public final void writeListSize(final int listSize) throws WriterAdapterException {
        Assert.greaterOrEqual(listSize, 0, "");

        try {
            this.dataOutput.writeInt(listSize);
        } catch (IOException e) {
            throw new WriterAdapterException(e);
        }
    }

}
