package org.geektime.tdd.container;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ComponentConstructionTest {

    interface Engine {
        String getName();
    }

    static class V6Engine implements Engine {
        @Override
        public String getName() {
            return "V6";
        }
    }

    static class V8Engine implements Engine {
        @Override
        public String getName() {
            return "V8";
        }
    }

    interface Car {
        Engine getEngine();
    }

    @Nested
    public class ConstructorInjection {
        static class CarInjectConstructor implements Car {
            private Engine engine;

            @Inject
            public CarInjectConstructor(Engine engine) {
                this.engine = engine;
            }

            @Override
            public Engine getEngine() {
                return this.engine;
            }
        }

        @Test
        public void constructor_injection() {
            Injector injector = Guice.createInjector(new AbstractModule() {
                @Override
                protected void configure() {
                    bind(Engine.class).to(V8Engine.class);
                    bind(Car.class).to(CarInjectMethod.class);
                }
            });

            Car car = injector.getInstance(Car.class);
            assertEquals("V8", car.getEngine().getName());
        }

        static class CarInjectField implements Car {
            @Inject
            private Engine engine;

            @Override
            public Engine getEngine() {
                return this.engine;
            }
        }

        static class CarInjectMethod implements Car {

            private Engine engine;

            @Override
            public Engine getEngine() {
                return this.engine;
            }

            @Inject
            public void install(Engine engine) {
                this.engine = engine;
            }
        }
    }

    @Nested
    public class DependencySelection {
        static class A {
            private B b;

            @Inject
            public A(B b) {
                this.b = b;
            }

            public B getB() {
                return this.b;
            }
        }
        static class B {
            private A a;

            @Inject
            public B(A a) {
                this.a = a;
            }

            public A getA() {
                return this.a;
            }
        }

        @Test
        public void cycle_dependencies() {
            AbstractModule module = new AbstractModule() {
                @Override
                protected void configure() {
                    bind(A.class);
                    bind(B.class);
                }
            };

            Injector injector = Guice.createInjector(module);

            A a = injector.getInstance(A.class);
            assertNotNull(a.getB());
        }
    }

}
