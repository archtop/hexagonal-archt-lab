module buckpal.account.adapter.in.web {
    exports buckpal.account.adapter.in.web;
    opens buckpal.account.adapter.in.web;
    requires spring.web;
    requires buckpal.common;
    requires buckpal.account.domain;
    requires buckpal.account.application.port.in;
}