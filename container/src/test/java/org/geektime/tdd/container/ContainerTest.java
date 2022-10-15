package org.geektime.tdd.container;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

    interface Component {}
    @Nested
    class ComponentConstruction {
        //TODO: instance
        @Test
        void should_bind_type_to_a_specific_instance() {
            Context context = new Context();

            Component instance = new Component() {};
            context.bind(Component.class, instance);

            assertSame(instance, context.get(Component.class));
        }

        //TODO: abstract class
        //TODO: interface
        @Nested
        class ConstructorInjection {
            //TODO: No args constructor
            //TODO: With Dependencies
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
