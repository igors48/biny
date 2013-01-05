package biny.core;

import biny.core.util.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 05.01.13
 */
public class FieldsReader {

    public static List<FieldData> readFields(Object from) throws ReflectorException, IllegalAccessException {
        Assert.notNull(from);

        List<FieldData> fieldDatas = new ArrayList<FieldData>();

        List<Field> fields = Reflector.getFields(from.getClass());

        for (Field field : fields) {
            Class type = field.getType();
            FieldData data = null;

            if (type.getName().equalsIgnoreCase("long")) {
                data = new FieldData<Long>(field.getName(), (Long) field.get(from));
            }

            if (type == String.class) {
                data = new FieldData<String>(field.getName(), (String) field.get(from));
            }

            if (data != null) {
                fieldDatas.add(data);
            }
        }

        return fieldDatas;
    }

}
