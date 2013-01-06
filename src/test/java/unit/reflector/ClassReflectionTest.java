package unit.reflector;

import biny.core.Reflector;
import biny.core.ReflectorException;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 05.01.13
 */
public class ClassReflectionTest {

    @Test
    public void allFieldReflectedInConstructorParameterOrder() throws ReflectorException {
        List<Field> fields = Reflector.createClassMetaData(TestValue.class).fields;

        Assert.assertEquals(2, fields.size());
        Assert.assertEquals(TestValue.LONG_VALUE_NAME, fields.get(0).getName());
        Assert.assertEquals(TestValue.STRING_VALUE_NAME, fields.get(1).getName());
    }

    @Test
    public void identifierReadCorrectly() throws ReflectorException {
        int identifier = Reflector.createClassMetaData(TestValue.class).identifier;

        Assert.assertEquals(TestValue.IDENTIFIER, identifier);
    }

}
