package biny.core.context;

import biny.core.util.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static biny.core.context.ContextException.canNotCreateClassDescriptor;
import static biny.core.context.Reflector.getClassName;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 15.01.13
 */
public final class ContextTools {

    public static void validate(final Set<String> availableClassNames, final Map<String, ClassDescriptor> descriptors) throws ContextException {
        Assert.notNull(availableClassNames);
        Assert.notNull(descriptors);

        Set<Integer> identifiers = new HashSet<Integer>();

        for (ClassDescriptor descriptor : descriptors.values()) {

            if (identifiers.contains(descriptor.identifier)) {
                throw ContextException.duplicateClassIdentifierFound(descriptor.identifier);
            } else {
                identifiers.add(descriptor.identifier);
            }
        }
    }

    public static Map<String, ClassDescriptor> createClassDescriptors(final Set<String> availableClassNames, final Class... classes) throws ContextException {
        Assert.notNull(availableClassNames);

        Map<String, ClassDescriptor> result = new HashMap<String, ClassDescriptor>();

        for (Class clazz : classes) {
            ClassDescriptor descriptor;
            descriptor = createClassDescriptor(clazz, availableClassNames);
            result.put(getClassName(clazz), descriptor);
        }

        return result;
    }

    public static Set<String> createAvailableClassNames(final Class... classes) {
        Set<String> availableClassNames = new HashSet<String>();

        for (Class clazz : classes) {
            String name = getClassName(clazz);
            availableClassNames.add(name);
        }

        return availableClassNames;
    }

    private static ClassDescriptor createClassDescriptor(final Class clazz, final Set<String> availableClassNames) throws ContextException {

        try {
            return Reflector.createClassDescriptor(clazz, availableClassNames);
        } catch (ReflectorException e) {
            throw canNotCreateClassDescriptor(clazz, e);
        }
    }

    private ContextTools() {
        // empty
    }

}
