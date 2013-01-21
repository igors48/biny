package biny.core.meta;

import biny.core.Type;
import biny.core.util.Assert;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 14.01.13
 */
public class AggregateListElement extends AbstractListElement {

    public final String className;

    public AggregateListElement(String className) {
        super(Type.AGGREGATE);

        Assert.isValidString(className);
        this.className = className;
    }

}
