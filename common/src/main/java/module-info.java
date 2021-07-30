module buckpal.common {
    exports buckpal.common;
    requires java.validation;
    requires transitive java.annotation;
    requires transitive lombok;
    requires transitive org.slf4j;
    requires transitive spring.core;
    requires transitive spring.beans;
    requires transitive spring.context;
}