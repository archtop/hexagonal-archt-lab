module buckpal.account.adapter.out.persistence {
    exports buckpal.account.adapter.out.persistence;
    requires java.persistence;
    requires spring.context;
    requires spring.data.commons;
    requires spring.data.jpa;
    requires buckpal.common;
    requires buckpal.account.domain;
    requires buckpal.account.application.port.out;
}