//: com.archtop.inheritance.rules.PortInheritanceRules.java

package com.archtop.inheritance.rules;


import com.archtop.application.port.in.SendMoneyUseCase;
import com.archtop.application.port.out.LoadAccountPort;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;


public class PortInheritanceRules {

    @ArchTest
    public static final ArchRule IN_PORT_INHERITANCE_RULE = classes()
            .that()
            .implement(SendMoneyUseCase.class)
            .should()
            .haveSimpleNameEndingWith("Service");

    @ArchTest
    public static final ArchRule OUT_PORT_INHERITANCE_RULE = classes()
            .that()
            .implement(LoadAccountPort.class)
            .should()
            .haveSimpleNameEndingWith("PersistenceAdapter");

}///:~