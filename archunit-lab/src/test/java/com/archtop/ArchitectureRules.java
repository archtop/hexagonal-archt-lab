//: com.archtop.ArchitectureRules.java

package com.archtop;


import static com.tngtech.archunit.library.Architectures.onionArchitecture;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;


public class ArchitectureRules {

    @ArchTest
    public static final ArchRule ARCHITECTURE_RULES = onionArchitecture()
            .domainModels("com.archtop.domain..")
            .domainServices("com.archtop.application.service..")
            .applicationServices("com.archtop.application..")
            .adapter(
                    "web",
                    "com.archtop.adapter.in.web..")
            .adapter(
                    "persistence",
                    "com.archtop.adapter.out.persistence..");

}///:~