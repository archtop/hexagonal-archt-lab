# Enforcing Architecture Boundaries

## Overview

### Architecture tends to Erode over time
- Boundaries between layers weaken
- Code becomes harder to test
- Generally need more and more time to implement new features

### Enforce the Boundaries within the Architecture
- #### to fight architecture erosion


> erode: v. /ɪˈrəʊd/ to gradually destroy the surface of something through the 
> action of wind, rain, etc.; to be gradually destroyed in this way

> erosion n. /ɪˈrəʊʒn/ the process by which the surface of something gradually 
> destroyed through the action of wind, rain, etc.


## Boundaries and Dependencies

| ![Enforcing_Architecture_Boundaries](images/Enforcing_Architecture_Boundaries.png "Enforcing_Architecture_Boundaries") |
| --- |
> Enforcing that dependencies point in the right direction
> Red-Dashed Arrows mark dependencies that are not allowed according to the 
> architecture 

### The innermost layer contains Domain Entities

### The App Layer may access those Domain Entities 
- to implement use case within Application Services

### Driving Adapters access Services through Incoming Ports

### Services may access Driven Adapters through Output Ports

### The Config Layer contains Factories creating Adapters and Services
- to provide them to a Dependency-Injection mechanism

### There is a Boundary between each layer and its next Inward neighbor

### There is a Boundary between each layer and its next Outward neighbor

### Dependencies crossing Layer Boundary must ALWAYS POINT INWARDS

### Make sure that there are no illegal dependencies 
- that point in the wrong direction


## Visibility Modifiers

| ![img.png](img.png)
| --- |

### The package-private modifier uses Java packages to group classes 
- into cohesive modules
- Classes within such a module, can access each other, 
- Classes within such a module, cannot be accessed from outside the package

### Make the classes in the persistence package package-private
- marked with "o" 

### Make the classes in the service package package-private
- marked with "o"

### Dependency Injection mechanisms use reflection to instantiate classes 
- so they will still be able to instantiate those classes even if they’re 
  package-private

### With Spring, this package-private approach only works 
- if we use the ___Classpath Scanning___ approach

### Other approaches require us to create instances of those objects ourselves
- which requires ___public access___

### The rest of the classes have to be public (marked with “+”)
- the ___domain package___ needs to be accessible by the other layers
- the ___Port Interfaces___ in application layer needs to be accessible 
    - by the web and persistence adapters

### The package-private modifier is awesome for small modules 
- with no more than a couple handful of classes

### It ___grows confusing___ to have so ___many classes in the same package___
- once a package reaches a certain number of classes 

### A ~~failed solution~~ for the big private package
- Create sub-packages, but this is where the package-private modifier fails to 
  deliver
    - Java treats sub-packages as different packages 
    - The parent package cannot access a package-private member of its sub-package
- so, members in sub-packages must be public, exposing them to the outside world 
- making the architecture vulnerable to illegal dependencies


## Post-Compile Checks

### ArchUnit provides an API 
- #### checks if dependencies point in the expected direction
- #### If it finds a violation, it will throw an exception

### Resources
- #### [Introduction to ArchUnit](https://www.baeldung.com/java-archunit-intro)
- #### [ArchUnit User Guide](https://www.archunit.org/userguide/html/000_Index.html)
- #### [ArchUnit API](https://javadoc.io/doc/com.tngtech.archunit/archunit/latest/index.html)
- #### [ArchUnit Maven plugin](https://github.com/societe-generale/arch-unit-maven-plugin)
- #### [ArchUnit-Examples](https://github.com/TNG/ArchUnit-Examples)

### To include ArchUnit’s JUnit 5 support
- Simply add the following dependency

``` 
<dependency>
    <groupId>com.tngtech.archunit</groupId>
    <artifactId>archunit-junit5</artifactId>
    <version>0.20.0</version>
    <scope>test</scope>
</dependency>
```

### Post-Compile Checks are Vulnerable
- A single typo or, more importantly, a single refactoring renaming a package, 
  can make the whole test useless
- Post-compile checks always have to be maintained parallel to the codebase


## Build Artifacts

### Enforce the Dependencies and Boundaries between Modules and Layers
### For each such Module or Layer, create a Separate Build Module 
- with its own codebase and its own build artifact (JAR file) as a result 
- In the build script of each module, specify only those dependencies to other 
  modules that are allowed according to our architecture
- Developers can no longer inadvertently create illegal dependencies 
    - because the classes are not even available on the classpath

| ![Build_Artifacts_for_Enforcing_Boundries](images/Build_Artifacts_for_Enforcing_Boundries.png "Build_Artifacts_for_Enforcing_Boundries") |
| --- |

### Multiple build modules for each Adapter

### Do not use Domain Model as transfer objects within in/out ports

### The Adapter Modules and the App Module may access the API Module
- but not the other way around

### The API Module does not have access to the Domain Model 

### The API Module cannot use the Domain Model within the port interfaces

### The Adapters have no access to the Domain Model and Services
- they must go through the ports

### One API Module contains only the Incoming Ports

### One API Module contains only the Outgoing Ports

### Incoming Adapters declare a dependency only to the Input Ports

### Outgoing Adapters declare a dependency only to the Output Ports

### The Domain Model doesn't access the Services Module

### Allow different Services to use the same Domain Model 
- by simply declaring a dependency to the domain build artifact

### The Gist
- #### The finer the modules, the stronger the Control
- #### The finer the modules, the more mappings to do between those Modules

### Advantages
- #### Build tools don’t allow circular dependencies
    - Circular Dependencies are BAD 
        - A change in one module within the circle would potentially mean a 
          change in all other modules within the circle which is a violation of 
          the Single Responsibility Principle
    - The Java compiler doesn’t care at all if there is a circular dependency 
      between two or more packages
- #### Build modules allow isolated code changes within certain modules 
    - without having to take the other modules into consideration
- #### Adding a new dependency becomes a conscious act instead of an accident 
    - A developer who needs access to a certain class he currently cannot access 
      will hopefully give some thought to the question if the dependency is 
      really reasonable before adding it to the build-script

### The Architecture should be somewhat Stable 
- before splitting it into different build modules


## How Does This Help Me Build Maintainable Software

### Software architecture is basically all about Managing Dependencies 
- between architecture elements

### If the Dependencies become a Big Ball of Mud
- the Architecture becomes a Big Ball of Mud

### Continually make sure that dependencies point in the right direction 
- to preserve the architecture over time 

### Keep the package structure in mind and use PACKAGE-PRIVATE visibility 
- when possible to avoid dependencies to classes that should not be accessed 
  from outside the package

### Use ArchUnit if Package Private Modifier doesn’t work

### Extract Architecture Elements into their own Build Modules 
- anytime the architecture is stable enough 
- because this gives explicit control over the dependencies
