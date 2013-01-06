package biny.core;

import biny.core.util.Assert;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 05.01.13
 */
public class FieldData<T> {

    public final String name;
    public final Type type;
    public final T value;

    public FieldData(String name, Type type, T value) {
        Assert.isValidString(name);
        this.name = name;

        Assert.notNull(type);
        this.type = type;

        Assert.notNull(value);
        this.value = value;
    }

}
