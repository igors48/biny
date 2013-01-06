package unit.reflector;

import biny.core.annotation.Field;
import biny.core.annotation.Identifier;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 04.01.13
 */
@Identifier(Value.IDENTIFIER)
public class Value {

    public static final String LONG_VALUE_NAME = "longValue";
    public static final String STRING_VALUE_NAME = "stringValue";
    public static final int IDENTIFIER = 42;

    public final long longValue;
    public final String stringValue;

    public Value(@Field(LONG_VALUE_NAME) long longValue,
                 @Field(STRING_VALUE_NAME) String stringValue) {
        this.longValue = longValue;
        this.stringValue = stringValue;
    }

}
