package unit.reflector;

import biny.core.annotation.Field;
import biny.core.annotation.Identifier;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 04.01.13
 */
@Identifier(SimpleObject.IDENTIFIER)
public class SimpleObject {

    public static final String LONG_VALUE_NAME = "longValue";
    public static final String STRING_VALUE_NAME = "stringValue";
    public static final int IDENTIFIER = 42;
    public static final int LONG_VALUE = 48;
    public static final String STRING_VALUE = "text";
    public static final SimpleObject SIMPLE_OBJECT = new SimpleObject(LONG_VALUE, STRING_VALUE);

    public final long longValue;
    public final String stringValue;

    public SimpleObject(@Field(LONG_VALUE_NAME) long longValue,
                        @Field(STRING_VALUE_NAME) String stringValue) {
        this.longValue = longValue;
        this.stringValue = stringValue;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SimpleObject that = (SimpleObject) o;

        if (longValue != that.longValue) return false;

        if (!stringValue.equals(that.stringValue)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (longValue ^ (longValue >>> 32));
        result = 31 * result + stringValue.hashCode();

        return result;
    }
}
