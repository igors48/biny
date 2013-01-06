package biny.core;

import biny.core.util.Assert;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public class ObjectWriter {

    private final ObjectWriterAdapter writer;

    public ObjectWriter(ObjectWriterAdapter writer) {
        Assert.notNull(writer);
        this.writer = writer;
    }

    public void write(Object object) {
        Assert.notNull(object);
    }

}
