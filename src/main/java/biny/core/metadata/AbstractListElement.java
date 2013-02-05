package biny.core.metadata;

import biny.core.util.Assert;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 14.01.13
 */
public abstract class AbstractListElement {

    public final Type type;

    protected AbstractListElement(Type type) {
        Assert.notNull(type);
        this.type = type;
    }

}
