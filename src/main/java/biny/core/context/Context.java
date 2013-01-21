package biny.core.context;

import biny.core.ClassDescriptor;
import biny.core.util.Assert;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static biny.core.Reflector.getClassName;
import static biny.core.context.ContextTools.createAvailableClassNames;
import static biny.core.context.ContextTools.createClassDescriptors;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 07.01.13
 */
public class Context {

    private final Map<String, ClassDescriptor> descriptors;

    public Context(Class... classes) throws ContextException {
        Set<String> availableClassNames = createAvailableClassNames(classes);
        Map<String, ClassDescriptor> temporary = createClassDescriptors(availableClassNames, classes);

        this.descriptors = Collections.unmodifiableMap(temporary);
    }

    public ClassDescriptor getClassDescriptor(int identifier) throws ContextException {
        Assert.greater(identifier, 0, "");

        for (ClassDescriptor candidate : this.descriptors.values()) {

            if (candidate.identifier == identifier) {
                return candidate;
            }
        }

        throw ContextException.canNotFindDescriptorForClassWithId(identifier);
    }

    public ClassDescriptor getClassDescriptor(Class clazz) throws ContextException {
        Assert.notNull(clazz);

        String className = getClassName(clazz);
        ClassDescriptor descriptor = this.descriptors.get(className);

        if (descriptor == null) {
            throw ContextException.canNotFindDescriptorForClass(clazz);
        }

        return descriptor;
    }

}
