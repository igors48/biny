package biny.core.context;

import biny.core.ClassDescriptor;
import biny.core.Reflector;
import biny.core.ReflectorException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static biny.core.Reflector.getClassName;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 15.01.13
 */
public final class ContextTools {

    public static Map<String, ClassDescriptor> createClassDescriptors(Set<String> availableClassNames, Class... classes) throws ContextException {
        Map<String, ClassDescriptor> temporary = new HashMap<String, ClassDescriptor>();

        for (Class clazz : classes) {
            ClassDescriptor descriptor;
            descriptor = createClassDescriptor(clazz, availableClassNames);
            temporary.put(getClassName(clazz), descriptor);
        }

        return temporary;
    }

    public static Set<String> createAvailableClassNames(Class... classes) {
        Set<String> availableClassNames = new HashSet<String>();

        for (Class clazz : classes) {
            String name = getClassName(clazz);
            availableClassNames.add(name);
        }

        return availableClassNames;
    }

    private static ClassDescriptor createClassDescriptor(Class clazz, Set<String> availableClassNames) throws ContextException {

        try {
            return Reflector.createClassDescriptor(clazz, availableClassNames);
        } catch (ReflectorException e) {
            throw ContextException.canNotCreateClassDescriptor(clazz, e);
        }
    }

    private ContextTools() {
        // empty
    }

}
