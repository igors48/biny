package unit.reflector.validation;

import biny.core.Reflector;
import biny.core.ReflectorException;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 04.01.13
 */
public class ClassValidationTest {

    @Test
    public void allConstructorParametersMustBeAnnotatedWithField() {


    }

    @Test(expected = ReflectorException.class)
    public void onlyOneConstructorMustBeDeclared() throws ReflectorException {
        Set<String> availableClassesNames = new HashSet<String>();
        Reflector.createClassDescriptor(TwoConstructor.class, availableClassesNames);
    }

    //all constructor parameters must have associated public final fields with same type
    //class must contains public final fields only
    //members must be long or String or Aggregate

}
