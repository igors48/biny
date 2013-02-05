package unit.adapter;

import biny.core.reader.ReaderAdapter;
import biny.core.reader.ReaderAdapterException;
import biny.core.reader.adapter.InputStreamAdapter;
import biny.core.writer.WriterAdapter;
import biny.core.writer.WriterAdapterException;
import biny.core.writer.adapter.OutputStreamAdapter;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 02.02.13
 */
public class StreamAdaptersTest {

    private static final int IDENTIFIER = 48;
    private static final long LONG_VALUE = 50L;
    private static final String STRING_VALUE = "string";
    private static final int LIST_SIZE = 52;

    @Test
    public void smoke() throws WriterAdapterException, ReaderAdapterException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        WriterAdapter writerAdapter = new OutputStreamAdapter(byteArrayOutputStream);

        writerAdapter.writeAggregateIdentifier(IDENTIFIER);
        writerAdapter.writeLong(LONG_VALUE);
        writerAdapter.writeString(STRING_VALUE);
        writerAdapter.writeListSize(LIST_SIZE);

        byte[] dump = byteArrayOutputStream.toByteArray();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(dump);

        ReaderAdapter readerAdapter = new InputStreamAdapter(byteArrayInputStream);

        Assert.assertEquals(IDENTIFIER, readerAdapter.readAggregateIdentifier());
        Assert.assertEquals(LONG_VALUE, readerAdapter.readLong());
        Assert.assertEquals(STRING_VALUE, readerAdapter.readString());
        Assert.assertEquals(LIST_SIZE, readerAdapter.readListLength());
    }
}
