module buckpal.common {
    exports buckpal.common;
    requires java.validation;
    requires transitive java.annotation;
    requires transitive lombok;
    requires transitive org.slf4j;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
}