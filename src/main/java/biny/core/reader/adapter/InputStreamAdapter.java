package biny.core.reader.adapter;

import biny.core.reader.ReaderAdapter;
import biny.core.reader.ReaderAdapterException;
import biny.core.util.Assert;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 02.02.13
 */
public class InputStreamAdapter implements ReaderAdapter {

    private final DataInput dataInput;

    public InputStreamAdapter(InputStream inputStream) {
        Assert.notNull(inputStream);

        this.dataInput = new DataInputStream(inputStream);
    }

    @Override
    public int readAggregateIdentifier() throws ReaderAdapterException {

        try {
            return this.dataInput.readInt();
        } catch (IOException e) {
            throw new ReaderAdapterException(e);
        }
    }

    @Override
    public long readLong() throws ReaderAdapterException {

        try {
            return this.dataInput.readLong();
        } catch (IOException e) {
            throw new ReaderAdapterException(e);
        }
    }

    @Override
    public String readString() throws ReaderAdapterException {

        try {
            return this.dataInput.readUTF();
        } catch (IOException e) {
            throw new ReaderAdapterException(e);
        }
    }

    @Override
    public int readListLength() throws ReaderAdapterException {

        try {
            return this.dataInput.readInt();
        } catch (IOException e) {
            throw new ReaderAdapterException(e);
        }
    }

}
