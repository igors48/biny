package unit.context;

import biny.core.context.Context;
import biny.core.context.ContextException;
import org.junit.Test;
import unit.reflector.SimpleObject;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 14.01.13
 */
public class ContextTest {

    // if after context creation unknown object found then exception will thrown
    // if duplicated ids is found then exception occurs

    @Test
    public void whenClassAddedToContextItsDescriptorBecomeAvailableForId() throws ContextException {
        Context context = new Context(SimpleObject.class);

        context.getClassDescriptor(SimpleObject.IDENTIFIER);
    }

    @Test
    public void whenClassAddedToContextItsDescriptorBecomeAvailableForClass() throws ContextException {
        Context context = new Context(SimpleObject.class);

        context.getClassDescriptor(SimpleObject.class);
    }

}
