package unit.reflector;

import biny.core.FieldData;
import biny.core.FieldsReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 05.01.13
 */
public class FieldsReaderTest {

    private static final long LONG_VALUE = 48;
    private static final String STRING_VALUE = "text";

    @Test
    public void allFieldsReaded() throws Exception {
        SimpleObject value = new SimpleObject(LONG_VALUE, STRING_VALUE);

        List<FieldData> fieldDatas = FieldsReader.readFields(value);

        Assert.assertEquals(2, fieldDatas.size());

        Assert.assertEquals(SimpleObject.LONG_VALUE_NAME, fieldDatas.get(0).name);
        Assert.assertEquals(LONG_VALUE, fieldDatas.get(0).value);

        Assert.assertEquals(SimpleObject.STRING_VALUE_NAME, fieldDatas.get(1).name);
        Assert.assertEquals(STRING_VALUE, fieldDatas.get(1).value);
    }

}
