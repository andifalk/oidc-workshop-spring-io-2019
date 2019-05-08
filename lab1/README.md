# Lab 1: Creating an OAuth 2.0/OIDC compliant Resource Server


## The workshop application

In this workshop you will be provided a finished but completely unsecured spring mvc web application. 
This library server application provides a RESTful service for administering books and users.

Use cases of this application are:

* Administer books (Creating/editing/deleting books)
* List available books
* Borrow a book
* Return a borrowed book
* Administer library users 

### Architecture

The RESTful service for books and users is build using the Spring MVC annotation model.

The application contains a complete documentation for the RESTful API build with spring rest docs which you can find in the directory build/asciidoc/html5 after performing a full gradle build.

The domain model of this application is quite simple and just consists of Book and User. 
The packages of the application are organized as follows:

* api: Contains the complete RESTful service
* business: All the service classes (quite simple for workshop, usually containing business logic)
* dataaccess: All domain models and repositories
* config: All spring configuration classes

### REST API

To call the provided REST API you can use curl or httpie. 
For details on how to call the REST API please consult the [REST API documentation](../docs/api-doc.html) which also provides sample requests for curl and httpie.

### User roles

There are three target user roles for this application:

Library_User: A standard user can borrow and return his currently borrowed books
Library_Curator: A curator user can add or delete books
Library_Admin: An administrator user can add or remove users

In the next labs we will build an OAuth2/OIDC resource server (lab 1) and an OAuth2/OIDC client application.
We will use [Keycloak](https://keycloak.org) as identity provider.

![Spring IO Workshop 2019](../docs/images/demo-architecture.png)

## Lab 1 instructions

Lab-1 is split into two parts:

1. Build a basic resource server using automatic mapping (from scopes inside tokens to authorities in spring)
2. Enhance the resource server with custom user  & authorities mapping and validation of token audience

Please start lab 1 using the _library-server-initial_ application. If you look inside the initial application
you will notice that this app is already secured using basic authentication and authorization with static roles.


![Spring IO Workshop 2019](automatic_role_mapping.png)

![Spring IO Workshop 2019](manual_role_mapping.png)

```
http --form http://localhost:8080/auth/realms/workshop/protocol/openid-connect/token grant_type=password \
username=bwayne password=wayne client_id=library-client client_secret=9584640c-3804-4dcd-997b-93593cfb9ea7
``` 


```
http localhost:9091/library-service/books 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cC...'
```

```
HTTP/1.1 200 OK
Content-Type: application/json

{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgO...",
    "expires_in": 300,
    "not-before-policy": 1556650611,
    "refresh_expires_in": 1800,
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCIg...",
    "scope": "profile email user",
    "session_state": "c92a82d1-8e6d-44d7-a2f3-02f621066968",
    "token_type": "bearer"
}
```





