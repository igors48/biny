package unit.context;

import biny.core.annotation.Field;
import biny.core.annotation.Identifier;
import unit.reflector.SimpleObject;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 23.01.13
 */
@Identifier(SimpleObject.IDENTIFIER)
public class ClassWithDuplicateIdentifier {

    public static final String LONG_VALUE_NAME = "longValue";
    public static final String STRING_VALUE_NAME = "stringValue";

    public final long longValue;
    public final String stringValue;

    public ClassWithDuplicateIdentifier(@Field(LONG_VALUE_NAME) long longValue,
                                        @Field(STRING_VALUE_NAME) String stringValue) {
        this.longValue = longValue;
        this.stringValue = stringValue;
    }

}
