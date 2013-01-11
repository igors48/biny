package biny.core.meta;

import biny.core.util.Assert;

import java.lang.reflect.Field;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 10.01.13
 */
public class ListMetaData extends AbstractMetaData {

    public final Field contained;

    public ListMetaData(Field field, Field contained) {
        super(field);

        Assert.notNull(contained);
        this.contained = contained;
    }

}
