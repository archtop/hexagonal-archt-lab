module buckpal.account.application.service {
    exports buckpal.account.application.service;
    requires buckpal.common;
    requires buckpal.account.domain;
    requires buckpal.account.application.port.out;
    requires buckpal.account.application.port.in;
    requires java.transaction;
}