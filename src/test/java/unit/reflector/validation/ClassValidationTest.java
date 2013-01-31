package unit.reflector.validation;

import biny.core.Reflector;
import biny.core.ReflectorException;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static biny.core.Reflector.getClassName;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 04.01.13
 */
public class ClassValidationTest {

    @Test(expected = ReflectorException.class)
    public void allConstructorParametersMustBeAnnotatedWithField() throws ReflectorException {
        Set<String> availableClassesNames = new HashSet<String>();
        availableClassesNames.add(getClassName(ClassWithOneConstructorParameterWithoutAnnotation.class));

        Reflector.createClassDescriptor(ClassWithOneConstructorParameterWithoutAnnotation.class, availableClassesNames);
    }

    @Test(expected = ReflectorException.class)
    public void onlyOneConstructorMustBeDeclared() throws ReflectorException {
        Set<String> availableClassesNames = new HashSet<String>();
        availableClassesNames.add(getClassName(TwoConstructor.class));

        Reflector.createClassDescriptor(TwoConstructor.class, availableClassesNames);
    }

}
