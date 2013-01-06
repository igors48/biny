package biny.core;

import biny.core.util.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 05.01.13
 */
public class ObjectFactory {

    public static Object create(Class clazz, List<Object> parameters) throws ReflectorException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Assert.notNull(clazz);
        Assert.notNull(parameters);

        Constructor constructor = Reflector.findConstructor(clazz);

        return constructor.newInstance(parameters.toArray());
    }

}
