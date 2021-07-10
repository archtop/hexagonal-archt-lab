# The Lab of ArchUnit

## Overview

### A Common Problem faced by Development Organizations 

- #### Code implementations can often DIVERGE from the original Design and Architecture
- #### The problem is common enough, especially on Large Projects 
- #### A new tool has emerged to help 
    - Test that a Code Implementation is Consistent with the Originally Defined 
      Architecture


## Why Should Test Architecture?



## Getting Started

### To include ArchUnit’s JUnit 5 support
- simply add the following dependency

``` 
<dependency>
    <groupId>com.tngtech.archunit</groupId>
    <artifactId>archunit-junit5</artifactId>
    <version>0.19.0</version>
    <scope>test</scope>
</dependency>
```

### Importing Classes

#### ArchUnit provides infrastructure to import Java bytecode into Java code structures
- This can be done using the ``` ClassFileImporter ```

#### ```JavaClasses ``` represents a collection of elements of type ``` JavaClass ```

#### ``` JavaClass ``` represents a single imported class file

```
JavaClasses classes = new ClassFileImporter().importPackages("com.mycompany.myapp");
JavaClass clazz = classes.get(Object.class);
System.out.print(clazz.getSimpleName()); 
```

### Asserting (Architectural) Constraints

- #### To specify a Rule, use the class ``` ArchRuleDefinition ``` as Entry Point
  ``` 
    import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
    
    // ...
    
    ArchRule myRule = classes()
    .that().resideInAPackage("..service..")
    .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..");
  ```

- #### The object of type ``` ArchRule ``` can be evaluated against a set of imported classes 
    - ``` myRule.check(importedClasses); ```

### 3.3. Using ArchUnit with JUnit 5

#### Controlling the Import & Import Options
- only consider code that is directly supplied and does not come from JARs
``` 
@AnalyzeClasses(packages = {"com.myapp.subone", "com.myapp.subtwo"})
@AnalyzeClasses(importOptions = {DoNotIncludeTests.class, DoNotIncludeJars.class})
```

#### Controlling the Cache ``` cacheMode = CacheMode.PER_CLASS ```
- The Java classes imported during this test run will not be cached by location 
    - and just be reused within the same test class
- After all tests of this class have been run, the imported Java classes will 
  simply be dropped 

``` 
@AnalyzeClasses(packages = "com.myapp.special", cacheMode = CacheMode.PER_CLASS)
```

#### Ignoring Tests with ```  @ArchIgnore ```
- ``` @Disabled ``` of JUnit 5 has no effect here

``` 
@ArchIgnore
@ArchTest
public static final ArchRule rule2 = classes().should()...
```

#### Grouping Rules

1. A project might end up with different categories of rules
     - service rules
     - persistence rules

2. Write one class for each set of rules

3. Refer to those sets of rules from another tests
    ``` 
    public class ApplicationRules {
    
        @ArchTest
        public static final ArchRule ACCESSING_DRIVING_PORTS_RULE = classes()
                .that()
                .resideInAPackage("..port.in..")
                .should()
                .onlyBeAccessed()
                .byAnyPackage("..in.web..");
    
        @ArchTest
        public static final ArchRule SERVICE_RULE = classes()
                .that()
                .resideInAPackage("..service..")
                .should()
                .bePackagePrivate();
    
    }///:~

    @AnalyzeClasses(
        packages = "com.archtop",
        importOptions = {
            ImportOption.DoNotIncludeTests.class,
            ImportOption.DoNotIncludeJars.class,
            ImportOption.DoNotIncludeArchives.class})
    class DependencyRulesTest {
        @ArchTest
        static final ArchTests APP_RULES = ArchTests.in(ApplicationRules.class);
    }///:~
   
    ```

4. ``` DependencyRulesTest ``` will include all ```@ArchTest``` annotated 
   members within ```ApplicationRules```

----

## What to Check

### Package Dependency Checks

``` 
public class ApplicationRules {

    @ArchTest
    public static final ArchRule APP_LAYER_ACCESSING_RULE = noClasses()
            .that()
            .resideInAnyPackage("com.archtop.application..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("com.archtop.adapter..");
            
    @ArchTest
    public static final ArchRule DRIVEN_PORTS_RULE = noClasses()
            .that()
            .resideOutsideOfPackages("com.archtop.adapter.out..")
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
```

### Class Dependency Check

``` 
classes().that().haveNameMatching(".*Bar")
    .should().onlyHaveDependentClassesThat().haveSimpleName("Bar")
```

### Class and Package Containment Checks

``` 
public class PortContainmentRules {
    @ArchTest
    public static final ArchRule OUT_PORT_CONTAINMENT_RULE = classes()
            .that()
            .haveSimpleNameEndingWith("Port")
            .should()
            .resideInAPackage("com.archtop.application.port.out");

}///:~
```

### Inheritance Checks

``` 
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
```

``` 
    classes().that().areAssignableTo(EntityManager.class)
        .should()
        .onlyHaveDependentClassesThat()
        .resideInAnyPackage("..persistence..")
```

### Annotation Checks

``` 
classes().that()
        .areAssignableTo(EntityManager.class)
        .should()
        .onlyHaveDependentClassesThat()
        .areAnnotatedWith(Transactional.class)
```

### Layer Checks

``` 
public class LayerRules {

    public static final String WEB_LAYER = "WEB_LAYER";
    public static final String PERSISTENCE_LAYER = "PERSISTENCE_LAYER";
    public static final String APPLICATION_LAYER = "APPLICATION_LAYER";
    public static final String DOMAIN_LAYER = "DOMAIN_LAYER";

    @ArchTest
    public static final ArchRule LAYER_RULE = layeredArchitecture()
            .layer(WEB_LAYER).definedBy(
                    "com.archtop.adapter.in.web")
            .layer(PERSISTENCE_LAYER).definedBy(
                    "com.archtop.adapter.out.persistence")
            .layer(DOMAIN_LAYER).definedBy(
                    "com.archtop.domain")
            .layer(APPLICATION_LAYER).definedBy(
                    "com.archtop.application.port.in",
                    "com.archtop.application.port.out",
                    "com.archtop.application.service")
            .whereLayer(WEB_LAYER).mayNotBeAccessedByAnyLayer()
            .whereLayer(APPLICATION_LAYER).mayOnlyBeAccessedByLayers(
                    WEB_LAYER, PERSISTENCE_LAYER)
            .whereLayer(PERSISTENCE_LAYER).mayOnlyBeAccessedByLayers(
                    APPLICATION_LAYER)
            .whereLayer(DOMAIN_LAYER).mayOnlyBeAccessedByLayers(
                    APPLICATION_LAYER);
}///:~
```

### Cycle Checks


## Ideas & Concepts

### Overview
- #### The Core Layer
    - Deals with the basic infrastructure, i.e. how to import byte code into 
      Java objects
- #### The Lang Layer
    - Contains the rule syntax to specify architecture rules succinct
      > succinct adj. /səkˈsɪŋkt/ expressed clearly and in a few words
- #### The Library Layer
    - contains more complex predefined rules, like a layered architecture with 
      several layers

### Core 
- #### Much of ArchUnit’s core API resembles the Java Reflection API
- #### Being able to offer a lot of information about the static structure of a Java program
- #### ArchUnit provides the ClassFileImporter
    - which imports packages from the classpath
      ``` 
      JavaClasses classes = new ClassFileImporter().importPackages("com.mycompany.myapp");
      ```

> resemble v. /rɪˈzembl/ to look like or be similar to another person or thing

### Lang
- #### Offers a powerful syntax to express rules abstractly
- #### Composed as fluent APIs

### Library
- #### Offers predefined complex rules for typical architectural goals
    - For example a succinct definition of a layered architecture via package definitions
- #### Offer rules to slice the code base in a certain way
    - for example in different areas of the domain, 
    - enforce these slices to be acyclic or independent of each other


## The Core API

### Overview
#### The Core API is itself divided into the ___domain objects___, and the ___actual import___

### Import

- #### The backbone of the infrastructure is the ``` ClassFileImporter ```
  - Import packages directly
    ``` 
    JavaClasses importedClasses = new ClassFileImporter().importPackage("com.myapp");
    ```
  - Import packages from the classpath
    ``` 
    JavaClasses classes = new ClassFileImporter().importClasspath();
    ```
  - Import any path from the file system
    ``` 
    JavaClasses classes = new ClassFileImporter().importPath("/some/path/to/classes");
    ```
  - Specific locations can be filtered out
    ``` 
    static final String DI_CONFIG_CLASS_NAME_SUFFIX = "DiConfiguration";
    ImportOption ignoreTests = new ImportOption() {
        @Override
        public boolean includes(Location location) {
            return !location.contains(DI_CONFIG_CLASS_NAME_SUFFIX);
        }
    };
    ```
      - A Location is principally a URI; ArchUnit considers sources as File or 
        JAR URIs
        ``` 
        file:///home/dev/my/project/target/classes/some/Thing.class
        jar:file:///home/dev/.m2/repository/some/things.jar!/some/Thing.class
        ```
  - When using JUnit 5
    ``` 
    @AnalyzeClasses(
    packages = "com.archtop",
    importOptions = {
            ImportOption.DoNotIncludeTests.class,
            ImportOption.DoNotIncludeJars.class,
            ImportOption.DoNotIncludeArchives.class,
            IgnoreDiConfiguration.class})
    @DisplayName("Dependency Rules Test for Driving Ports - ")
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class ArchitectureTest {
    
        static final String DI_CONFIG_CLASS_NAME_SUFFIX = "DiConfiguration";
        ImportOption ignoreTests = new ImportOption() {
            @Override
            public boolean includes(Location location) {
                return !location.contains(DI_CONFIG_CLASS_NAME_SUFFIX);
            }
        };
        ... ... 
    }
    ```
- #### Dealing with Missing Classes
    - The normal situation: a class within the scope of the import has a 
      reference to a class outside the scope of the import
    - How to treat those classes that are missing from the import
        - ArchUnit searches within the classpath for missing classes and if 
          found imports them
    - This additional lookup from the classpath will cost some performance and 
      in some cases might not make sense
        - ArchUnit can be configured to create stubs instead
        - Check: Configuring the Resolution Behavior

### Domain

#### Overview
- The domain objects represent Java code
- The concept of accesses to another class
- ArchUnit imports the whole graph of classes and their relationship to each other

#### Domain Objects, Reflection and the Classpath
- More reading ... 


## The Lang API

### Composing Class Rules

- #### ".." in the package notation, which refers to any number of packages
    - Thus "..service.." just expresses "any package that contains some 
      sub-package 'service'", e.g. com.myapp.service.any
  ``` 
    noClasses()
        .that()
        .resideInAPackage("..service..")
        .or()
        .resideInAPackage("..persistence..")
        .should()
        .accessClassesThat()
        .resideInAPackage("..controller..")
        .orShould()
        .accessClassesThat()
        .resideInAPackage("..ui..")
    
    rule.check(importedClasses);
  ```

### Composing Member Rules
- #### An extended API to define rules for members of Java classes
    - For example:  if methods in a certain context need to be annotated with a 
      specific annotation, or return types implementing a certain interface
      ``` 
      ArchRule rule = ArchRuleDefinition.methods()
              .that()
              .arePublic()
              .and()
              .areDeclaredInClassesThat()
              .resideInAPackage("..controller..")
              .should()
              .beAnnotatedWith(Secured.class);

      rule.check(importedClasses);
      ```
    - ArchRuleDefinition also offers the methods 
        - members()  
        - fields() 
        - codeUnits() 
        - constructors()
        - noMembers()
        - noFields() 
        - noMethods()
        - etc.

### Creating Custom Rules
- #### Most architectural rules take the form
      ``` 
      classes that ${PREDICATE} should ${CONDITION}
      ```
    - Limit imported classes to a relevant subset, and then evaluate some 
      condition to see that all those classes satisfy it
- #### ArchUnit’s API exposes the concepts of 
    - ``` DescribedPredicate ``` 
    - ``` ArchCondition ```
      ``` 
        // define the predicate
        DescribedPredicate<JavaClass> resideInAPackageService = ...
        // define the condition
        ArchCondition<JavaClass> accessClassesThatResideInAPackageController = 
        
        noClasses()
            .that(resideInAPackageService)
            .should(accessClassesThatResideInAPackageController);
      ```
    - If the predefined API does not allow to express some concept, it is 
      possible to extend it in any custom way [Custom Rules](https://www.archunit.org/userguide/html/000_Index.html#_import)

### Predefined Predicates and Conditions
- #### Predicates defined in an inner class ``` Predicates ``` within the type they target
    ``` JavaClass.Predicates.simpleName(String) ```
- #### Predicates can be joined using the methods 
    - ``` predicate.or(other) ``` 
    - ``` predicate.and(other) ```
      ``` 
        import static com.tngtech.archunit.core.domain.JavaClass.Predicates.assignableTo;
        import static com.tngtech.archunit.core.domain.JavaClass.Predicates.simpleName;
        
        DescribedPredicate<JavaClass> serializableNamedFoo = simpleName("Foo")
               .and(assignableTo(Serializable.class));
      ```
- #### All predefined conditions can be found within ``` ArchConditions ```
  ``` 
  ArchCondition<JavaClass> callEquals =
      ArchConditions.callMethod(Object.class, "equals", Object.class);
  ArchCondition<JavaClass> callHashCode =
      ArchConditions.callMethod(Object.class, "hashCode");

  ArchCondition<JavaClass> callEqualsOrHashCode = callEquals.or(callHashCode);
  ```

### [Rules with Custom Concepts](https://www.archunit.org/userguide/html/000_Index.html#_controlling_the_rule_text)

### Controlling the Rule Text
- #### Document the reason for a rule rules if that rules are not common knowledge
  ``` 
    classes()
            .that(haveAFieldAnnotatedWithPayload)
            .should(onlyBeAccessedBySecuredMethods)
            .because("@Secured methods will be intercepted, checking for 
                      increased privileges and obfuscating sensitive auditing 
                      information");
  ```
- #### To completely overwrite the rule description
    -   if multiple predicates or conditions joined 
  ``` 
    classes()
            .that(haveAFieldAnnotatedWithPayload)
            .should(onlyBeAccessedBySecuredMethods)
            .as("Payload may only be accessed in a secure way");
  ```

### Ignoring Violations

- #### In legacy projects there might be too many violations to fix at once
    - Nevertheless, that code should be covered completely by architecture tests 
      to ensure that no further violations will be added to the existing code 
- #### One approach to ignore existing violations 
    - To tailor the that(..) clause of the rules in question to ignore certain 
      violations
    - A more generic approach is to ignore violations based on simple regex matches
    - Put a file named ``` archunit_ignore_patterns.txt ``` in the root of the 
      classpath
        - Every line will be interpreted as a regular expression and checked 
          against reported violations
    - Violations with a message matching the pattern will be ignored
    - If no violations left, the check will pass 


## The Library API

### Overview
- #### The Library API offers 
    - a growing collection of predefined rules
        - which offer a more concise API for more complex but common patterns
        - like a layered architecture or checks for cycles between slices

### Architectures
- #### The entrance point for checks of common architectural styles
    - ``` com.tngtech.archunit.library.Architectures ```
- #### Currently, only for Layered Architecture and Hexagonal Architecture
- #### Layered Architecture
- #### Onion Architecture
  ``` 
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
    
    }
  ```
    - The domain package is the core of the application
        - The ``` domainModels ``` packages contain the domain entities
        - The packages in ``` domainServices ``` contains services that use the 
          entities in the domainModel packages
    - The ``` applicationServices ``` packages contain services and 
      configuration to run the application and use cases 
        - NO any dependency from the domain to the application packages
    - The ``` adapter ``` package contains logic to connect to external systems 
      and/or infrastructure
        - No adapter may depend on another adapter
        - Adapters can use both the items of the domain, and the application packages
        - neither the domain nor the application packages must contain 
          dependencies on any adapter package

### Slices

#### Overview
- Two ``` slice ``` rules offered by the Library API
- The entrance point is 
  ``` 
  com.tngtech.archunit.library.dependencies.SlicesRuleDefinition
  ```
- Sort classes into slices according to one or several package infixes (中綴)
    - and then write assertions against those slices
    ``` 
    SlicesRuleDefinition.slices()
            .matching("..myapp.(*)..")
            .should()
            .beFreeOfCycles();
    SlicesRuleDefinition.slices()
            .matching("..myapp.(**)")
            .should()
            .notDependOnEachOther()
    ```

#### [Configurations](https://www.archunit.org/userguide/html/000_Index.html#_configurations) 
- Two configuration parameters to adjust the behavior of the cycle detection
    - can be configured via ``` archunit.properties ```
    ``` 
    # This will limit the maximum number of cycles to detect and thus required CPU and heap.
    # default is 100
    cycles.maxNumberToDetect=50
    
    # This will limit the maximum number of dependencies to report per cycle edge.
    # Note that ArchUnit will regardless always analyze all dependencies to detect cycles,
    # so this purely affects how many dependencies will be printed in the report.
    # Also note that this number will quickly affect the required heap since it scales with number.
    # of edges and number of cycles
    # default is 20
    cycles.maxNumberOfDependenciesPerEdge=5
    ```
### General Coding Rules
- #### The Library API offers a small set of coding rules
    - Can be found within ``` com.tngtech.archunit.library ```
- #### ``` GeneralCodingRules ``` containing a set of very general rules and conditions for coding
    - To check that classes do not access ``` System.out ``` or ``` System.err ```
        - but use logging instead 

### [DependencyRules](https://www.archunit.org/userguide/html/000_Index.html#_dependencyrules)
  - For example: To check that classes do not depend on classes from upper packages

### [ProxyRules](https://www.archunit.org/userguide/html/000_Index.html#_proxyrules)

### [PlantUML Component Diagrams as rules](https://www.archunit.org/userguide/html/000_Index.html#_plantuml_component_diagrams_as_rules)

### [Freezing Arch Rules](https://www.archunit.org/userguide/html/000_Index.html#_freezing_arch_rules)

### [Software Architecture Metrics](https://www.archunit.org/userguide/html/000_Index.html#_software_architecture_metrics)


## [JUnit Support](https://www.archunit.org/userguide/html/000_Index.html#_junit_4_5_support)


## [Advanced Configuration](https://www.archunit.org/userguide/html/000_Index.html#_advanced_configuration)


## The ``` PackageMatcher ```

- ### In particular ``` * ``` stands for any sequence of characters
- ### ``` .. ``` stands for any sequence of packages, including zero packages
    - ``` ..pack.. ``` matches 'a.pack', 'a.pack.b' or 'a.b.pack.c.d', 
       - but not 'a.packa.b'
    - ``` *.pack.* ``` matches 'a.pack.b', 
       - but not 'a.b.pack.c'  
    - ``` ..*pack*.. ``` matches 'a.prepackfix.b'
    - ``` *.*.pack*..```  matches 'a.b.packfix.c.d', 
       - but neither 'a.packfix.b' nor 'a.b.prepack.d'
- ### Capturing groups
    - ``` (*) ``` matches any sequence of characters, but not the dot '.`
    - ``` (**) ``` matches any sequence including the dot
    - ``` ..service.(*)..``` matches 'a.service.hello.b' 
       - and group 1 would be 'hello'
    - ``` ..service.(**) ```  matches 'a.service.hello.more' 
       - and group 1 would be 'hello.more'
    - ``` my.(*)..service.(**) ``` matches 
       - 'my.company.some.service.hello.more' 
       - and group 1 would be 'company', while group 2 would be 'hello.more'


    
## Resources
- ### [Introduction to ArchUnit](https://www.baeldung.com/java-archunit-intro)
- ### [ArchUnit User Guide](https://www.archunit.org/userguide/html/000_Index.html)
- ### [ArchUnit API](https://javadoc.io/doc/com.tngtech.archunit/archunit/latest/index.html)
- ### [ArchUnit Maven plugin](https://github.com/societe-generale/arch-unit-maven-plugin)
- ### [ArchUnit-Examples](https://github.com/TNG/ArchUnit-Examples)

