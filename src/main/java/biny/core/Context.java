package biny.core;

import biny.core.util.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 07.01.13
 */
public class Context {

    private final Map<Integer, ClassMetaData> metaData;

    public Context(Class... classes) throws ReflectorException {
        Map<Integer, ClassMetaData> temporary = new HashMap<Integer, ClassMetaData>();

        for (Class clazz : classes) {
            ClassMetaData classMetaData = Reflector.createClassMetaData(clazz);
            temporary.put(classMetaData.identifier, classMetaData);
        }

        this.metaData = Collections.unmodifiableMap(temporary);
    }

    public ClassMetaData getClassMetaData(int identifier) {
        Assert.greater(identifier, 0, "");

        return this.metaData.get(identifier);
    }

}
