[![License](https://img.shields.io/badge/License-Apache%20License%202.0-brightgreen.svg)][1]
[![Build Status](https://travis-ci.org/andifalk/oidc-workshop-spring-io-2019.svg?branch=master)](https://travis-ci.org/andifalk/oidc-workshop-spring-io-2019)

# Securing Microservices with OpenID Connect and Spring Security 5.1 @ Spring I/O 2019

![Spring IO Workshop 2019](docs/images/spring_io_2019_workshop.jpg)

Have you ever wondered what the heck is OpenID Connect and how it differs from OAuth 2.0? Are Grant Types, Flows, JOSE, JWT or JWK unknown beings for you?
Then this workshop is a great opportunity for you to get to know all these things by getting your hands dirty in code using Spring Security 5.1.

This repository contains the complete material for workshop at [Spring I/O 2019](https://2019.springio.net/) on [Securing Microservices with OpenID Connect and Spring Security 5.1](https://2019.springio.net/sessions/securing-microservices-with-openid-connect-and-spring-security-51-workshop)

## Workshop Contents

This workshop content is split up into the following parts:

* Introduction into [OAuth 2.0](https://tools.ietf.org/html/rfc6749) and [OpenID Connect 1.0](https://openid.net/specs/openid-connect-core-1_0.html):   
  [Presentation Slides (HTML5)](https://andifalk.github.io/oidc-workshop-spring-io-2019/)
  
* [Hands-On Part](https://github.com/andifalk/oidc-workshop-spring-io-2019#hands-on-part-labs) with Spring Security 5.1
  * Implementing a [GitHub](https://github.com) client using common [OAuth 2.0](https://tools.ietf.org/html/rfc6749) providers
  * Creating an [OpenID Connect 1.0](https://openid.net/specs/openid-connect-core-1_0.html) resource server
  * Mapping [OpenID Connect 1.0](https://openid.net/specs/openid-connect-core-1_0.html) claims to Spring Security authorities
  * Creating an [OpenID Connect 1.0](https://openid.net/specs/openid-connect-core-1_0.html) client
  * Testing a [OpenID Connect 1.0](https://openid.net/specs/openid-connect-core-1_0.html) resource server

* A look into the future of OAuth 2.0 and OIDC features to be released in Spring Security 5.2 and 5.3  
  [Presentation Slides (HTML5)](https://andifalk.github.io/oidc-workshop-spring-io-2019/#/5/)

## Requirements for Hands-On Part

To start the workshop you need:

* [Java JDK version 8 or 11](https://openjdk.java.net/install/)
* A Java IDE ([Eclipse](https://www.eclipse.org/), [STS](https://spring.io/tools), [IntelliJ](https://www.jetbrains.com/idea/), [VS Code](https://code.visualstudio.com/), [NetBeans](https://netbeans.org/), ...)
* A tool like [curl](https://curl.haxx.se/download.html), [HTTPie](https://httpie.org/) or [Postman](https://www.getpostman.com/) is helpful to play with the REST API services.  
* The [REST API documentation](https://andifalk.github.io/oidc-workshop-spring-io-2019/api-doc.html) of the initial library application
* [Keycloak](https://keycloak.org) as OpenID Connect/OAuth2 identity provider/authorization server (see below for setting this up)
* This GitHub repository: [(https://github.com/andifalk/oidc-workshop-spring-io-2019.git](https://github.com/andifalk/oidc-workshop-spring-io-2019.git)

## Intro Labs

[Intro Lab 1: Follow the OAuth 2.0 authorization code flow in detail](intro-labs/auth-code-demo)

[Intro Lab 2: Implementing a GitHub Client using common OAuth 2.0 providers](intro-labs/github-client)

## Hands-On Part Labs

The hands-on part of the workshop is split up into the following parts:

[Preparation: Setting up Keycloak as Identity Provider](setup_keycloak)

[Lab 1: Implementing an OAuth2/OIDC resource server](lab1)

[Lab 2: Implementing an OAuth2/OIDC client (authorization code flow)](lab2)

[Lab 3: Implementing an OAuth2/OIDC client (client credentials flow)](lab3)

[Lab 4: Testing JWT tokens](lab4)

## License

Apache 2.0 licensed

Copyright (c) by 2019 Andreas Falk

[1]:http://www.apache.org/licenses/LICENSE-2.0.txt
