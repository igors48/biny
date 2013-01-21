package biny.core.writer;

import biny.core.WriterAdapter;
import biny.core.meta.AbstractField;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 17.01.13
 */
public interface FieldWriter {

    void write(Object object, AbstractField field, WriterAdapter adapter) throws FieldWriterException;

}
