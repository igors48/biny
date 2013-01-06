package unit.writer.adapter;

import biny.core.util.Assert;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public class StringPart implements Part {

    public final String value;

    public StringPart(String value) {
        Assert.notNull(value);
        this.value = value;
    }

}
