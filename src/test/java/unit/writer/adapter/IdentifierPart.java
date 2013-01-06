package unit.writer.adapter;

import biny.core.util.Assert;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 06.01.13
 */
public class IdentifierPart implements Part {

    public final int identifier;

    public IdentifierPart(int identifier) {
        Assert.greater(identifier, 0, "");
        this.identifier = identifier;
    }

}
