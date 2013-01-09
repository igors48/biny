package biny.core;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public interface ObjectReaderAdapter {

    int readAggregateIdentifier();

    long readLong();

    String readString();

    int readListStart();

}
