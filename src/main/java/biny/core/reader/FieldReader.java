package biny.core.reader;

import biny.core.ReaderAdapter;
import biny.core.meta.AbstractField;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 17.01.13
 */
public interface FieldReader {

    Object read(AbstractField field, ReaderAdapter adapter) throws FieldReaderException;

}
