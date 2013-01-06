package biny.core.element;

import biny.core.AbstractElement;
import biny.core.util.Assert;

import java.util.Collections;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public class List extends AbstractElement {

    public final java.util.List<AbstractElement> elements;

    public List(String name, java.util.List<AbstractElement> elements) {
        super(name);

        Assert.notNull(elements);
        this.elements = Collections.unmodifiableList(elements);
    }

}
