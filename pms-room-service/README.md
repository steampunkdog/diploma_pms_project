# ROOMS SERVICE

Rooms Service is a part of microservice PMS system, responsible for the management of hotel Rooms Data


### ENVIRONMENT REQUIREMENTS
| Name | Version
| --- | --- |
| PostgreSQL Database | 12 |
| JDK | 15.0.2 |


### DATA MODEL DESCRIPTION

*Model interfaces:*
![Model interfaces!](./docs/img/room_model-Interfaces.png)
 
*Model implementation:*
![Model impl!](./docs/img/room_model-Impls.png)

The data model of this service is presented as a set of interfaces inheriting 
the base **BaseEntity** interface, and their implementations using the JPA notation to use the 
capabilities of Spring Data JPA. 

***Entity interfaces will be further transferred to the library for use in other services***


### CLASS STRUCTURE

#### Repositories Layer

![Repositories classes!](./docs/img/room_repositories.png)
 
 We are using Spring Data JPA, so for repository layer we need to only create interfaces describing API,
 and implementation will be generated automatically in runtime by Spring

#### Services Layer

*Service layer interfaces:*
![Services classes!](./docs/img/room_services-Interfaces.png)

*Service layer implementation:*
![Services classes!](./docs/img/room_services-Interfaces.png)

At the service level, we have generic interface BaseService describing CRUD methods for 
working with a generic data type, and BaseServiceImpl - which implements them using an untyped repository.

Service interfaces designed to work with certain types of entities (RoomClassService, RoomService, etc.) 
inherit BaseService by typing it with a specific data type, and their implementation classes inherit 
BaseServiceImpl to avoid code duplication.

#### Controllers Layer

***info will be provided soon***

