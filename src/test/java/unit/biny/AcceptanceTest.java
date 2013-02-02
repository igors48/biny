package unit.biny;

import biny.core.Biny;
import biny.core.BinyException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 02.02.13
 */
public class AcceptanceTest {

    @Test
    public void smoke() throws BinyException {
        Biny biny = new Biny(ComplexObject.class);

        ComplexObject fixture = new ComplexObject(48);
        byte[] dump = biny.write(fixture);
        ComplexObject restored = (ComplexObject) biny.read(dump);

        Assert.assertEquals(fixture, restored);
    }
}
