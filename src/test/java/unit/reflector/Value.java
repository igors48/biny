package unit.reflector;

import biny.core.Field;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 04.01.13
 */
public class Value {

    public static final String LONG_VALUE_NAME = "longValue";
    public static final String STRING_VALUE_NAME = "stringValue";

    public final long longValue;
    public final String stringValue;

    public Value(@Field(LONG_VALUE_NAME) long longValue,
                 @Field(STRING_VALUE_NAME) String stringValue) {
        this.longValue = longValue;
        this.stringValue = stringValue;
    }

}
