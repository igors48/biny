package biny.core.meta;

import biny.core.Type;

import java.lang.reflect.Field;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 10.01.13
 */
public class StringField extends AbstractField {

    public StringField(Field field) {
        super(Type.STRING, field);
    }

}
