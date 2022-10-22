package org.geektime.tdd.container;

import jakarta.inject.Provider;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private Map<Class<?>, Provider<?>> providers = new HashMap<>();
    public <ComponentType> void bind(Class<ComponentType> type, ComponentType instance) {
        providers.put(type, (Provider<ComponentType>)() -> instance);
    }

    public <ComponentType> ComponentType get(Class<ComponentType> type) {
        return (ComponentType) providers.get(type).get();
    }

    public <ComponentType, ComponentImplementation extends ComponentType>
    void bind(Class<ComponentType> type, Class<ComponentImplementation> implementation) {
        providers.put(type, (Provider<ComponentType>) () -> {
            try {
                return (ComponentType) ((Class<?>) implementation).getConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
