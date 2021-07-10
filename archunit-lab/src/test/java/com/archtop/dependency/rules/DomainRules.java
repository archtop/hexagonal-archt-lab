//: com.archtop.dependency.rules.DomainRules.java

package com.archtop.dependency.rules;


import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;


public class DomainRules {

    @ArchTest
    public static final ArchRule DOMAIN_ACCESSING_RULE = noClasses()
            .that()
            .resideInAnyPackage("com.archtop.domain..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage(
                    "com.archtop.application..",
                    "com.archtop.adapter..")
            .because("Domain should always be the core of the whole application.");

}///:~