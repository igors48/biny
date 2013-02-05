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

    private final Context context;
    private final ObjectWriter objectWriter;
    private final ObjectReader objectReader;

    public Biny(Class... classes) throws BinyException {
        try {
            this.context = new Context(classes);

            this.objectWriter = new ObjectWriter(this.context);
            this.objectReader = new ObjectReader(this.context);
        } catch (ContextException e) {
            throw new BinyException(e);
        }
    }

    public byte[] write(Object object) throws BinyException {
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

    public Object read(byte[] data) throws BinyException {
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
