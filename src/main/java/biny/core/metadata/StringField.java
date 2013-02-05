package biny.core.metadata;

import java.lang.reflect.Field;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 10.01.13
 */
public class StringField extends AbstractField {

    public StringField(final Field field) {
        super(Type.STRING, field);
    }

}
