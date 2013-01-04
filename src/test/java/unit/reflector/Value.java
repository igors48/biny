package unit.reflector;

import biny.core.Field;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 04.01.13
 */
public class Value {

    public final int intValue;
    public final String stringValue;

    public Value(@Field("intValue") int intValue,
                 @Field("stringValue") String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }

}
