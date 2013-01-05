package biny.core;

import biny.core.util.Assert;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 05.01.13
 */
public class FieldData<T> {

    public final String name;
    public final T value;

    protected FieldData(String name, T value) {
        Assert.isValidString(name);
        this.name = name;

        Assert.notNull(value);
        this.value = value;
    }

}
