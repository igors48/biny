package unit.reflector.reflection;

import biny.core.annotation.Field;
import biny.core.annotation.Identifier;
import unit.reflector.SimpleObject;

import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 13.01.13
 */
@Identifier(ReflectionTestFixture.IDENTIFIER)
public class ReflectionTestFixture {

    public static final int IDENTIFIER = 10;

    public static final String PRIMITIVE_LONG_FIELD_NAME = "primitiveLong";
    public static final String WRAPPED_LONG_FIELD_NAME = "wrappedLong";
    public static final String STRING_FIELD_NAME = "string";
    public static final String AGGREGATE_FIELD_NAME = "aggregate";
    public static final String LIST_WITH_WRAPPERS_FIELD_NAME = "listWithWrappers";
    public static final String LIST_WITH_AGGREGATES_FIELD_NAME = "listWithAggregates";
    public static final String LIST_WITH_STRINGS_FIELD_NAME = "listWithStrings";

    public final long primitiveLong;
    public final String string;
    public final Long wrappedLong;
    public final SimpleObject aggregate;
    public final List<Long> listWithWrappers;
    public final List<String> listWithStrings;
    public final List<SimpleObject> listWithAggregates;

    public ReflectionTestFixture(@Field(PRIMITIVE_LONG_FIELD_NAME) long primitiveLong,
                                 @Field(WRAPPED_LONG_FIELD_NAME) Long wrappedLong,
                                 @Field(STRING_FIELD_NAME) String string,
                                 @Field(AGGREGATE_FIELD_NAME) SimpleObject aggregate,
                                 @Field(LIST_WITH_WRAPPERS_FIELD_NAME) List<Long> listWithWrappers,
                                 @Field(LIST_WITH_STRINGS_FIELD_NAME) List<String> listWithStrings,
                                 @Field(LIST_WITH_AGGREGATES_FIELD_NAME) List<SimpleObject> listWithAggregates) {
        this.primitiveLong = primitiveLong;
        this.wrappedLong = wrappedLong;
        this.string = string;
        this.aggregate = aggregate;
        this.listWithWrappers = listWithWrappers;
        this.listWithStrings = listWithStrings;
        this.listWithAggregates = listWithAggregates;
    }

}
