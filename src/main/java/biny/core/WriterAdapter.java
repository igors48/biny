package biny.core;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public interface WriterAdapter {
    // TODO may be writeAggregateEnd ??? for JSON for example. Do not know...
    // TODO send names with values

    void writeAggregateIdentifier(int identifier);

    void writeLong(long value);

    void writeString(String value);

    void writeListSize(int listSize);

}
