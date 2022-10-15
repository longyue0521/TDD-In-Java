package org.geektime.tdd.container;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

    interface Component {}
    @Nested
    class ComponentConstruction {
        //TODO: instance
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
