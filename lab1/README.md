# Lab 1: Creating an OAuth 2.0/OIDC compliant Resource Server

## Lab Contents

* [The workshop application](#the-workshop-application)
  * [Architecture](#architecture)
  * [REST API](#rest-api)
  * [User roles](#user-roles)
* [The lab 1 tutorial](#lab-1-tutorial)
  * [Lab contents](#lab-contents)
  * [Part 1: Basic resource server w/ automatic scope mapping](#lab-1---part-1)
  * [Part 2: Enhanced resource server w/ custom user/authorities mapping](#lab-1---part-2)

## The workshop application

In this first workshop lab you will be provided a complete spring mvc web server application together
with a corresponding spring mvc thymeleaf web client app (which will come into play in [lab 2](../lab2/README.md)).  
The server application is already secured by basic authentication and also includes authorization using static roles. 

The server application provides a RESTful service for administering books and users 
(a very _lightweight_ books library).

Use cases of this application are:

* Administer books (Creating/editing/deleting books)
* List available books
* Borrow a book
* Return a borrowed book
* Administer library users 

### Architecture

The RESTful service for books and users is build using the Spring MVC annotation model and Spring HATEOAS.

The application also contains a complete documentation for the RESTful API that is automatically 
generated with spring rest docs. You can find this in the directory build/asciidoc/html5 after performing a full 
gradle build.

The domain model of this application is quite simple and just consists of _Book_ and _User_ models.   
The packages of the application are organized as follows:

* api: Contains the complete RESTful service
* business: The service classes (quite simple for workshop, usually these contain the business logic)
* common: Classes that are reused in multiple other packages
* dataaccess: All domain models and repositories
* config: All spring configuration classes
* security: All security relevant classes, e.g. a _UserDetailsService_ implementation

### REST API

To call the provided REST API you can use curl or httpie. 
For details on how to call the REST API please consult the [REST API documentation](../docs/api-doc.html) which also provides sample requests for curl and httpie.

### User roles

There are three target user roles for this application:

* LIBRARY_USER: Standard library user who can list, borrow and return his currently borrowed books
* LIBRARY_CURATOR: A curator user who can add, edit or delete books
* LIBRARY_ADMIN: An administrator user who can list, add or remove users

__Important:__ We will use the following users in all subsequent labs from now on:

| Username | Email                    | Role            |
| ---------| ------------------------ | --------------- |
| bwayne   | bruce.wayne@example.com  | LIBRARY_USER    |
| bbanner  | bruce.banner@example.com | LIBRARY_USER    |
| pparker  | peter.parker@example.com | LIBRARY_CURATOR |
| ckent    | clark.kent@example.com   | LIBRARY_ADMIN   |

These users are configured for basic authentication and also later for authenticating using keycloak.

In the next labs we will build an OAuth2/OIDC resource server (lab 1) and an OAuth2/OIDC client application.
We will use [Keycloak](https://keycloak.org) as identity provider.  
Please again make sure you have setup
keycloak as described in [Setup Keycloak](../setup_keycloak/README.md)

## Lab 1 Tutorial

Lab-1 is actually split into two parts:

1. Build a basic resource server using __automatic mapping__
 (Automatically maps scopes inside JWT tokens to corresponding authorities in spring security)
2. Enhance the resource server of step 1 with __custom user & authorities mapping__ 
and also implement additional validation of _audience_ claim in access token 

### Contents of lab 1 folder

In the lab 1 folder you find 3 applications:

* __library-server-initial__: This is the application we will use as starting point for this lab
* __library-server-complete-automatic__: This application is the completed one for the __first part__ of this lab 
* __library-server-complete-manual__: This application is the completed one for the __second part__ of this lab 

### Lab 1 - Part 1

Now, let's start with part 1 of this lab. Here we will implement just the minimal required additions to get an 
OAuth2/OIDC compliant resource server with automatic mapping of token scopes to spring security authorities.

![Spring IO Workshop 2019](../docs/images/automatic_role_mapping.png)

#### Explore the initial application

Please navigate your Java IDE to the lab1/library-server-initial project and at first explore this project a bit.  
Then start the application by running the class _com.example.library.server.Lab1InitialLibraryServerApplication_.

To test if the application works as expected, either

* open a web browser and navigate to [localhost:9091/library-server/books](http://localhost:9091/library-server/books)
  and use 'bruce.wayne@example.com' and 'wayne' as login credentials
* or use a command line like curl or httpie or postman (if you like a UI)

Httpie:
```bash
http localhost:9091/library-server/books --auth 'bruce.wayne@example.com:wayne'
``` 

Curl:
```bash
curl http://localhost:9091/library-server/books -u bruce.wayne@example.com:wayne | jq
```

If this succeeds you should see a list of books in JSON format.  
Also try the same request with user credentials of 'peter.parker@example.com / parker'.

__Question: What response would you expect here?__

Finally also try same request without specifying any user:

```bash
http localhost:9091/library-server/books
``` 

Then you should see the following response:

```http request
HTTP/1.1 401 
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Content-Type: application/json;charset=UTF-8
WWW-Authenticate: Basic realm="Realm"
{
    "error": "Unauthorized",
    "message": "Unauthorized",
    "path": "/library-server/books",
    "status": 401,
    "timestamp": "2019-05-09T17:26:17.571+0000"
}
``` 

__<u>Step 1: Configure as resource server</u>__  
To change this application into a resource server you have to make changes in the dependencies 
of the gradle build file _build.gradle_:

Remove this dependency:
```groovy
implementation('org.springframework.boot:spring-boot-starter-security')
```
and add this dependency instead:
```groovy
implementation ('org.springframework.boot:spring-boot-starter-oauth2-resource-server')
```

Spring security 5 uses the 
[OpenID Connect Discovery](https://openid.net/specs/openid-connect-discovery-1_0.html#ProviderConfig) specification 
to completely configure the resource server to use our keycloak instance.
  
__Make sure you have startet keycloak as described in the [setup section](../setup_keycloak/README.md).__

Navigate your web browser to the url [localhost:8080/auth/realms/workshop/.well-known/openid-configuration](http://localhost:8080/auth/realms/workshop/.well-known/openid-configuration).
Then you should see the public discovery information that keycloak provides.

```json
{
  "issuer": "http://localhost:8080/auth/realms/workshop",
  "authorization_endpoint": "http://localhost:8080/auth/realms/workshop/protocol/openid-connect/auth",
  "token_endpoint": "http://localhost:8080/auth/realms/workshop/protocol/openid-connect/token",
  "userinfo_endpoint": "http://localhost:8080/auth/realms/workshop/protocol/openid-connect/userinfo",
  "jwks_uri": "http://localhost:8080/auth/realms/workshop/protocol/openid-connect/certs",
  ...
}  
```

For configuring a resource server the important entries are _issuer_ and _jwks_uri_.  
Spring Security 5 automatically configures a resource server by just specifying the _issuer_ uri value 
as part of the predefined spring property _spring.security.oauth2.resourceserver.jwt.issuer-uri_ 

To perform this step, open _application.yml__ and add the issuer uri property. 
After adding this it should look like this:

```yaml
spring:
  jpa:
    open-in-view: false
  jackson:
    date-format: com.fasterxml.jackson.databind.util.StdDateFormat
    default-property-inclusion: non_null
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://localhost:8080/auth/realms/workshop
```
An error you get very often with files in yaml format is that the indents are not correct. 
This can lead to unexpected errors later when you try to run all this stuff.


### Lab 1 - Part 2

Now, let's start with part 1 of this lab. Here we will implement just the minimal required additions to get an 
OAuth2/OIDC compliant resource server with automatic mapping of token scopes to spring security authorities.



![Spring IO Workshop 2019](../docs/images/manual_role_mapping.png)

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





