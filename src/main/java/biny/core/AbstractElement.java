package biny.core;

import biny.core.util.Assert;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public abstract class AbstractElement {

    public final String name;

    protected AbstractElement(String name) {
        Assert.isValidString(name);
        this.name = name;
    }

}
