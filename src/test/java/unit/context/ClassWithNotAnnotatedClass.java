package unit.context;

import biny.core.annotation.Field;
import biny.core.annotation.Identifier;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 23.01.13
 */
@Identifier(50)
public class ClassWithNotAnnotatedClass {

    public final NotAnnotatedClass notAnnotatedClass;

    public ClassWithNotAnnotatedClass(@Field("notAnnotatedClass") NotAnnotatedClass notAnnotatedClass) {
        this.notAnnotatedClass = notAnnotatedClass;
    }

}
