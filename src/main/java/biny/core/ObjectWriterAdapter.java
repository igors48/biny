package biny.core;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public interface ObjectWriterAdapter {

    void writeAggregateIdentifier(int identifier);

    void writeLong(long value);

    void writeString(String value);

}
