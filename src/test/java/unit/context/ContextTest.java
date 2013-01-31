package unit.context;

import biny.core.context.Context;
import biny.core.context.ContextException;
import org.junit.Before;
import org.junit.Test;
import unit.reflector.SimpleObject;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 14.01.13
 */
public class ContextTest {

    private static final int INVALID_IDENTIFIER = 165;
    private static final Class<ContextTest> INVALID_CLASS = ContextTest.class;

    private Context context;

    @Before
    public void before() throws ContextException {
        this.context = new Context(SimpleObject.class);
    }

    @Test
    public void whenClassAddedToContextItsDescriptorBecomeAvailableForId() throws ContextException {
        context.getClassDescriptor(SimpleObject.IDENTIFIER);
    }

    @Test
    public void whenClassAddedToContextItsDescriptorBecomeAvailableForClass() throws ContextException {
        context.getClassDescriptor(SimpleObject.class);
    }

    @Test(expected = ContextException.class)
    public void whenClassWithInvalidIdentifierRequestedThenExceptionThrown() throws ContextException {
        context.getClassDescriptor(INVALID_IDENTIFIER);
    }

    @Test(expected = ContextException.class)
    public void whenNotRegisteredClassRequestedThenExceptionThrown() throws ContextException {
        context.getClassDescriptor(INVALID_CLASS);
    }

}
