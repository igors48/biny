package unit.reflector.creation;

import biny.core.ObjectFactory;
import biny.core.ReflectorException;
import org.junit.Assert;
import org.junit.Test;
import unit.reflector.SimpleObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 05.01.13
 */
public class ObjectFactoryTest {

    private static final long LONG_VALUE = 48L;
    private static final String STRING_VALUE = "string";

    @Test
    public void objectCreatedFromFieldDatas() throws InvocationTargetException, InstantiationException, ReflectorException, IllegalAccessException {
        List<Object> fieldDatas = new ArrayList<Object>();
        fieldDatas.add(LONG_VALUE);
        fieldDatas.add(STRING_VALUE);

        SimpleObject value = (SimpleObject) ObjectFactory.create(SimpleObject.class, fieldDatas);

        Assert.assertEquals(LONG_VALUE, value.longValue);
        Assert.assertEquals(STRING_VALUE, value.stringValue);
    }

}
