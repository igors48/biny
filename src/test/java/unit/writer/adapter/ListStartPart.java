package unit.writer.adapter;

import biny.core.util.Assert;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 07.01.13
 */
public class ListStartPart implements Part {

    public final int listSize;

    public ListStartPart(int listSize) {
        Assert.greaterOrEqual(listSize, 0, "");
        this.listSize = listSize;
    }

}
