package unit.reflector;

import biny.core.Reflector;
import biny.core.ReflectorException;
import org.junit.Test;

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
        Reflector.parse(TwoConstructor.class);
    }

    //only one constructor must be declared
    //all constructor parameters must have associated public final fields with same type
    //class must contains public final fields only
    //members must be long or String

}
