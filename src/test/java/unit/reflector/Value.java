package unit.reflector;

import biny.core.Field;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 04.01.13
 */
public class Value {

    public final long longValue;
    public final String stringValue;

    public Value(@Field("longValue") int longValue,
                 @Field("stringValue") String stringValue) {
        this.longValue = longValue;
        this.stringValue = stringValue;
    }

}
