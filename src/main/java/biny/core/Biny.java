package biny.core;

import biny.core.context.Context;
import biny.core.context.ContextException;
import biny.core.reader.ObjectReader;
import biny.core.reader.ObjectReaderException;
import biny.core.reader.ReaderAdapter;
import biny.core.reader.adapter.InputStreamAdapter;
import biny.core.util.Assert;
import biny.core.writer.ObjectWriter;
import biny.core.writer.ObjectWriterException;
import biny.core.writer.WriterAdapter;
import biny.core.writer.adapter.OutputStreamAdapter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 02.02.13
 */
public class Biny {

    private final ObjectWriter objectWriter;
    private final ObjectReader objectReader;

    public Biny(final Class... classes) throws BinyException {

        try {
            Context context = new Context(classes);

            this.objectWriter = new ObjectWriter(context);
            this.objectReader = new ObjectReader(context);
        } catch (ContextException e) {
            throw new BinyException(e);
        }
    }

    public final byte[] write(final Object object) throws BinyException {
        Assert.notNull(object);

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            WriterAdapter writerAdapter = new OutputStreamAdapter(byteArrayOutputStream);

            this.objectWriter.write(object, writerAdapter);

            return byteArrayOutputStream.toByteArray();
        } catch (ObjectWriterException e) {
            throw new BinyException(e);
        }
    }

    public final Object read(final byte[] data) throws BinyException {
        Assert.notNull(data);

        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            ReaderAdapter readerAdapter = new InputStreamAdapter(byteArrayInputStream);

            return this.objectReader.read(readerAdapter);
        } catch (ObjectReaderException e) {
            throw new BinyException(e);
        }
    }

}
