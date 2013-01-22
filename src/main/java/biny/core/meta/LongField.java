package biny.core.meta;

import biny.core.Type;

import java.lang.reflect.Field;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 10.01.13
 */
public class LongField extends AbstractField {

    public LongField(Field field) {
        super(Type.LONG, field);
    }

}