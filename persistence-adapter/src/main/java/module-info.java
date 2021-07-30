module buckpal.account.adapter.out.persistence {
    exports buckpal.account.adapter.out.persistence;
    opens buckpal.account.adapter.out.persistence;
    requires java.persistence;
    requires java.sql;
    requires spring.core;
    requires spring.context;
    requires spring.beans;
    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.jdbc;
    requires buckpal.common;
    requires buckpal.account.domain;
    requires buckpal.account.application.port.out;
    requires com.h2database;
    requires spring.orm;
    requires spring.tx;
}