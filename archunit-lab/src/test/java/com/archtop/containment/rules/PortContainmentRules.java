//: com.archtop.containment.rules.PortContainmentRules.java

package com.archtop.containment.rules;


import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;


public class PortContainmentRules {

    @ArchTest
    public static final ArchRule OUT_PORT_CONTAINMENT_RULE = classes()
            .that()
            .haveSimpleNameEndingWith("Port")
            .should()
            .resideInAPackage("com.archtop.application.port.out");

    @ArchTest
    public static final ArchRule IN_PORT_CONTAINMENT_RULE = classes()
            .that()
            .haveSimpleNameEndingWith("UseCase")
            .should()
            .resideInAPackage("com.archtop.application.port.in");

}///:~