package biny.core.metadata;

import biny.core.util.Assert;

import java.lang.reflect.Field;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 10.01.13
 */
public class ListField extends AbstractField {

    public final AbstractListElement element;

    public ListField(final AbstractListElement element, final Field field) {
        super(Type.LIST, field);

        Assert.notNull(element);
        this.element = element;
    }

}
