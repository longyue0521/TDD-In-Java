package org.geektime.tdd.container;

import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

Context context;
@BeforeEach
void setup() {
   context = new Context();
}
    @Nested
    class ComponentConstruction {
        //TODO: instance
        @Test
        void should_bind_type_to_a_specific_instance() {
            Component instance = new Component() {};
            context.bind(Component.class, instance);

            assertSame(instance, context.get(Component.class));
        }

        //TODO: abstract class
        //TODO: interface
        @Nested
        class ConstructorInjection {
            //TODO: No args constructor
            @Test
            void should_bind_type_to_a_class_with_default_constructor() {
               context.bind(Component.class, ComponentWithDefaultConstructor.class);

               Component instance = context.get(Component.class);

               assertNotNull(instance);
               assertTrue(instance instanceof ComponentWithDefaultConstructor);
            }
            //TODO: With Dependencies
            @Test
            void should_bind_type_to_a_class_with_inject_constructor() {
                Dependency dependency = new Dependency() {
                };

                context.bind(Component.class, ComponentWithInjectConstructor.class);
                context.bind(Dependency.class, dependency);

                Component instance = context.get(Component.class);

                assertNotNull(instance);
                assertSame(dependency, ((ComponentWithInjectConstructor)instance).getDependency());
            }
            //TODO: A -> B -> C
        }

        @Nested
        class FieldInjection {

        }

        @Nested
        class MethodInjection {

        }

    }

    @Nested
    class DependenciesSelection {

    }

    @Nested
    class LifecycleManagement {

    }
}

interface Component {}

 class ComponentWithDefaultConstructor implements Component {
    public ComponentWithDefaultConstructor() {}
}

interface Dependency {

}
class ComponentWithInjectConstructor implements Component {

    private Dependency dependency;

    @Inject
    public ComponentWithInjectConstructor(Dependency dependency) {
        this.dependency = dependency;
    }

    public Dependency getDependency() {
        return dependency;
    }
}