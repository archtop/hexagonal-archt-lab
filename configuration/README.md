# [Assembling the Application](https://github.com/archtop/hexagonal-archt-lab/blob/main/notes/11_Assembling_the_Application.md)

- ### [Spring IoC & DI](../doc/notes/Spring_DI_Java_Config.md)

## Only Use Spring Boot Dependencies in Module 
  - ### ``` Configuration ```


## Do Not Make Module ``` Configuration ``` be Java Module


## Java Module Files

### ``` common ``` Module
``` 
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
```

### ``` domain ``` Module
``` 
module buckpal.account.domain {
    exports buckpal.account.domain;
    requires buckpal.common;
}
```

### ``` application ``` Module
``` 
module buckpal.account.application.service {
    exports buckpal.account.application.service;
    requires buckpal.common;
    requires buckpal.account.domain;
    requires buckpal.account.application.port.out;
    requires buckpal.account.application.port.in;
    requires java.transaction;
}
```

### ``` api-in ``` Module
``` 
module buckpal.account.application.port.in {
    exports buckpal.account.application.port.in;
    requires java.validation;
    requires buckpal.common;
    requires buckpal.account.domain;
}
```

### ``` api-out ``` Module
``` 
module buckpal.account.application.port.out {
    exports buckpal.account.application.port.out;
    requires buckpal.common;
    requires buckpal.account.domain;
}
```

### ``` persistence-adapter ``` Module
``` 
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
```

### ``` web-adapter ``` Module
``` 
module buckpal.account.adapter.in.web {
    exports buckpal.account.adapter.in.web;
    requires spring.web;
    requires buckpal.common;
    requires buckpal.account.domain;
    requires buckpal.account.application.port.in;
}
```

