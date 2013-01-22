package biny.core;

import biny.core.meta.AbstractField;
import biny.core.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 05.01.13
 */
public class ClassDescriptor {

    // TODO store constructor

    public final int identifier;
    public final String name;
    public final List<AbstractField> fields;

    public ClassDescriptor(int identifier, String name, List<AbstractField> fields) {
        Assert.greater(identifier, 0, "");
        this.identifier = identifier;

        Assert.isValidString(name);
        this.name = name;

        Assert.notNull(fields);
        this.fields = Collections.unmodifiableList(fields);
    }

}
