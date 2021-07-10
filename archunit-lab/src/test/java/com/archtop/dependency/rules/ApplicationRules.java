//: com.archtop.dependency.rules.ApplicationRules.java

package com.archtop.dependency.rules;


import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;


public class ApplicationRules {

    @ArchTest
    public static final ArchRule APP_LAYER_ACCESSING_RULE = noClasses()
            .that()
            .resideInAnyPackage("com.archtop.application..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("com.archtop.adapter..");

    @ArchTest
    public static final ArchRule DRIVEN_PORTS_RULE = classes()
            .that()
            .resideInAnyPackage("com.archtop.adapter.out..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("com.archtop.application.port.out..");

    @ArchTest
    public static final ArchRule SERVICE_ACCESS_RULE = noClasses()
            .that()
            .resideOutsideOfPackages("com.archtop.application.service..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("com.archtop.application.service..");


}///:~