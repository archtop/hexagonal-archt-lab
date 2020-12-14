# What's Wrong with Layers
--------------------------------------------------------------------------------

| ![Three Layers Architecture](images/3_Layers_Archt.png "Three Layers") |
| --- |

## How Layers Work Together?
    - The web layer that receives requests and routes them to a service in 
      the domain or “business” layer
    - The service does some business magic and calls components from the 
      persistence layer to query for or modify the current state of our domain 
      entities

## The Desired Result of Layers
    - To build domain logic that is independent of the web and persistence layers
    - The web or persistence technologies can be switched without affecting the 
      domain logic 
    - Add new features without affecting existing features
    - Keep options open and are able to quickly adapt to changing requirements 
      and external factors

> **keep/leave your options open**: to avoid making a decision now so that you 
> still have a choice in the future

## The Truth
A Layered-Architecture has ***Too Many Open Flanks*** that allow bad habits to 
creep in and make the software ***Increasingly Harder to Change Over Time***


### Layered-Architecture Promotes Database-Driven Design

1. ***WHAT*** we’re trying ***TO ACHIEVE*** with almost any application we’re 
   building
    - ***CREATE a MODEL of the RULES or “POLICIES”*** that 
      ***GOVERN the BUSINESS*** in order to make it easier for the users to 
      interact with them
    - ***MODEL BEHAVIOR***, and ***NOT STATE*** 
         - Even state is an important part of any application
         - ***THE BEHAVIOR IS WHAT CHANGES THE STATE***
         - ***THE BEHAVIOR DRIVES THE BUSINESS*** 

2. The foundation of a conventional layered architecture is the database
   - The web layer depends on the domain layer which in turn depends on the
     persistence layer and thus the database
   - Everything builds on top of the persistence layer

3. Why are we making the database the foundation of our architecture and not the 
   domain logic?
   - We are with the natural flow of dependencies of a conventional layered 
     architecture
   - It makes absolutely no sense from a business point of view

4. We should ***BUILD THE DOMAIN LOGIC BEFORE DOING ANYTHING ELSE***
   - Only then can we find out if we have understood it correctly 
   - Only once we know we’re ***BUILDING THE RIGHT DOMAIN LOGIC***, should we 
     move on to build a persistence and web layer around it

> **Only once ..., should ...** : 只有一旦 ... 才能 ... 

5. The use of Object-Relational Mapping (ORM) with a layered architecture makes
   us easily be tempted to ~~mix business rules with persistence aspects~~

> Don’t get me wrong (不要誤會我), I love ORM, and I’m working with JPA and 
> Hibernate on a daily basis
