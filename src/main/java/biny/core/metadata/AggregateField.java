package biny.core.metadata;

import biny.core.util.Assert;

import java.lang.reflect.Field;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 10.01.13
 */
public class AggregateField extends AbstractField {

    public final String className;

    public AggregateField(String className, Field field) {
        super(Type.AGGREGATE, field);

        Assert.isValidString(className);
        this.className = className;
    }

}
