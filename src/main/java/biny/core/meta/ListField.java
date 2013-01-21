package biny.core.meta;

import biny.core.Type;
import biny.core.util.Assert;

import java.lang.reflect.Field;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 10.01.13
 */
public class ListField extends AbstractField {

    public final AbstractListElement element;

    public ListField(AbstractListElement element, Field field) {
        super(Type.LIST, field);

        Assert.notNull(element);
        this.element = element;
    }

}
