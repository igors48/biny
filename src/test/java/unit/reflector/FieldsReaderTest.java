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

    @Test
    public void allFieldsReaded() throws Exception {
        Value value = new Value(48, "text");

        List<FieldData> fieldDatas = FieldsReader.readFields(value);

        Assert.assertEquals(2, fieldDatas.size());

        Assert.assertEquals("longValue", fieldDatas.get(0).name);
        Assert.assertEquals(48, fieldDatas.get(0).value);

        Assert.assertEquals("stringValue", fieldDatas.get(0).name);
        Assert.assertEquals("text", fieldDatas.get(0).value);
    }

}
