package biny.core;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public interface WriterAdapter {
    // TODO may be writeAggregateEnd ??? for JSON for example. Do not know...
    // TODO send names with values

    void writeAggregateIdentifier(int identifier) throws WriterAdapterException;

    void writeLong(long value) throws WriterAdapterException;

    void writeString(String value) throws WriterAdapterException;

    void writeListSize(int listSize) throws WriterAdapterException;

}
