package unit.reflector.validation;

import biny.core.annotation.Field;
import biny.core.annotation.Identifier;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 31.01.13
 */
@Identifier(56)
public class ClassWithOneConstructorParameterWithoutAnnotation {

    public final long first;
    public final long second;

    public ClassWithOneConstructorParameterWithoutAnnotation(
            @Field("first") long first,
            long second) {
        this.first = first;
        this.second = second;
    }

}
