package biny.core.element;

import biny.core.AbstractElement;
import biny.core.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public class Aggregate extends AbstractElement {

    public final List<Value> values;
    public final List<Aggregate> aggregates;

    public Aggregate(String name, List<Value> values, List<Aggregate> aggregates) {
        super(name);

        Assert.notNull(values);
        this.values = Collections.unmodifiableList(values);

        Assert.notNull(aggregates);
        this.aggregates = Collections.unmodifiableList(aggregates);
    }

}
