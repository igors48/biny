package unit.biny;

import biny.core.Biny;
import biny.core.BinyException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 02.02.13
 */
public class AcceptanceTest {

    @Test
    public void smoke() throws BinyException {
        Biny biny = new Biny(ComplexObject.class, FirstAggregate.class, SecondAggregate.class);

        SecondAggregate secondAggregate = new SecondAggregate("second_text");
        FirstAggregate firstAggregate = new FirstAggregate(50, secondAggregate);
        List<Long> listOfValues = Arrays.asList(1L, 2L, 3L);
        List<SecondAggregate> listOfAggregates = Arrays.asList(new SecondAggregate("first"), new SecondAggregate("second"), new SecondAggregate("third"));
        ComplexObject fixture = new ComplexObject(48, "string", firstAggregate, listOfValues, listOfAggregates);
        byte[] dump = biny.write(fixture);
        ComplexObject restored = (ComplexObject) biny.read(dump);

        Assert.assertEquals(fixture, restored);
    }
}
