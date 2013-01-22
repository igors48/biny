package unit.reflector.reflection;

import biny.core.Reflector;
import biny.core.ReflectorException;
import biny.core.context.ContextException;
import biny.core.meta.*;
import org.junit.Assert;
import org.junit.Test;
import unit.reflector.SimpleObject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static biny.core.Reflector.getClassName;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 05.01.13
 */
public class ClassReflectionTest {

    @Test
    public void allFieldReflectedInConstructorParameterOrder() throws ReflectorException, ContextException {
        Set<String> availableClassesNames = createAvailableClasses();

        List<AbstractField> fields = Reflector.createClassDescriptor(ReflectionTestFixture.class, availableClassesNames).fields;

        Assert.assertEquals(7, fields.size());

        Assert.assertEquals(ReflectionTestFixture.PRIMITIVE_LONG_FIELD_NAME, fields.get(0).getName());
        Assert.assertTrue(fields.get(0) instanceof LongField);

        Assert.assertEquals(ReflectionTestFixture.WRAPPED_LONG_FIELD_NAME, fields.get(1).getName());
        Assert.assertTrue(fields.get(1) instanceof LongField);

        Assert.assertEquals(ReflectionTestFixture.STRING_FIELD_NAME, fields.get(2).getName());
        Assert.assertTrue(fields.get(2) instanceof StringField);

        Assert.assertEquals(ReflectionTestFixture.AGGREGATE_FIELD_NAME, fields.get(3).getName());
        Assert.assertTrue(fields.get(3) instanceof AggregateField);

        Assert.assertEquals(ReflectionTestFixture.LIST_WITH_WRAPPERS_FIELD_NAME, fields.get(4).getName());
        Assert.assertTrue(fields.get(4) instanceof ListField);
        Assert.assertTrue(((ListField) fields.get(4)).element instanceof LongListElement);

        Assert.assertEquals(ReflectionTestFixture.LIST_WITH_STRINGS_FIELD_NAME, fields.get(5).getName());
        Assert.assertTrue(fields.get(5) instanceof ListField);
        Assert.assertTrue(((ListField) fields.get(5)).element instanceof StringListElement);

        Assert.assertEquals(ReflectionTestFixture.LIST_WITH_AGGREGATES_FIELD_NAME, fields.get(6).getName());
        Assert.assertTrue(fields.get(6) instanceof ListField);
        Assert.assertTrue(((ListField) fields.get(6)).element instanceof AggregateListElement);
        AggregateListElement listElementMetaData = (AggregateListElement) ((ListField) fields.get(6)).element;
        Assert.assertEquals(getClassName(SimpleObject.class), listElementMetaData.className);
    }

    @Test
    public void identifierReadCorrectly() throws ReflectorException, ContextException {
        Set<String> availableClassesNames = createAvailableClasses();

        int identifier = Reflector.createClassDescriptor(ReflectionTestFixture.class, availableClassesNames).identifier;

        Assert.assertEquals(ReflectionTestFixture.IDENTIFIER, identifier);
    }

    private Set<String> createAvailableClasses() {
        Set<String> availableClassesNames = new HashSet<String>();

        availableClassesNames.add(getClassName(ReflectionTestFixture.class));
        availableClassesNames.add(getClassName(SimpleObject.class));

        return availableClassesNames;
    }

}
