package unit.context;

import biny.core.context.Context;
import biny.core.context.ContextException;
import org.junit.Test;
import unit.reflector.SimpleObject;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 23.01.13
 */
public class ContextValidationTest {

    @Test(expected = ContextException.class)
    public void ifDuplicatedIdsIsFoundThenExceptionOccurs() throws ContextException {
        new Context(SimpleObject.class, ClassWithDuplicateIdentifier.class);
    }

    @Test(expected = ContextException.class)
    public void ifAfterContextCreationUnknownClassFoundThenExceptionOccurs() throws ContextException {
        new Context(ClassWithNotAnnotatedClass.class, NotAnnotatedClass.class);
    }

}
