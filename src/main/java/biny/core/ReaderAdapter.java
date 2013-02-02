package biny.core;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public interface ReaderAdapter {

    int readAggregateIdentifier() throws ReaderAdapterException;

    long readLong() throws ReaderAdapterException;

    String readString() throws ReaderAdapterException;

    int readListLength() throws ReaderAdapterException;

}
