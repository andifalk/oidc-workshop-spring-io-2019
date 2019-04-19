[![License](https://img.shields.io/badge/License-Apache%20License%202.0-brightgreen.svg)][1]
[![Build Status](https://travis-ci.org/andifalk/oidc-workshop-spring-io-2019.svg?branch=master)](https://travis-ci.org/andifalk/oidc-workshop-spring-io-2019)

# Securing Microservices with OpenID Connect and Spring Security 5.1 @ Spring I/O 2019

This repository contains material for workshop at [Spring I/O 2019](https://2019.springio.net/) on "Securing Microservices with OpenID Connect and Spring Security 5.1"

## Topics

Topics that will be covered by this workshop are:

* Introduction into [OAuth 2.0](https://tools.ietf.org/html/rfc6749) and [OpenID Connect 1.0](https://openid.net/specs/openid-connect-core-1_0.html)
* Implementing a simple [OAuth 2.0](https://tools.ietf.org/html/rfc6749) client for the GitHub
* Creating an [OpenID Connect 1.0](https://openid.net/specs/openid-connect-core-1_0.html) resource server
* Mapping [OpenID Connect 1.0](https://openid.net/specs/openid-connect-core-1_0.html) claims to Spring Security authorities
* Creating an [OpenID Connect 1.0](https://openid.net/specs/openid-connect-core-1_0.html) client

## Requirements

To start the workshop you need:

* [Java JDK version 8 or 11](https://openjdk.java.net/install/)
* A Java IDE ([Eclipse](https://www.eclipse.org/), [STS](https://spring.io/tools), [IntelliJ](https://www.jetbrains.com/idea/), [VS Code](https://code.visualstudio.com/), [NetBeans](https://netbeans.org/), ...)
* To test the RESTful services on the command line [curl](https://curl.haxx.se/download.html) would be helpful to install
* The [REST API documentation](https://andifalk.github.io/oidc-workshop-spring-io-2019/api-doc.html) of the initial application
* This GitHub repository: [(https://github.com/andifalk/oidc-workshop-spring-io-2019.git](https://github.com/andifalk/oidc-workshop-spring-io-2019.git)

## Introduction to OAuth 2.0 and OpenID Connect

[Presentation Slides (HTML5)](https://andifalk.github.io/oidc-workshop-spring-io-2019/)

## Workshop structure

The workshop is split up into the following parts:

[Lab 1: Implementing a GitHub Client with OAuth 2.0](lab1/README.md)

[Lab 2: Implementing an OIDC resource server](lab2/README.md)

[Lab 3: Implementing an OIDC client](lab3/README.md)

## License

Apache 2.0 licensed

Copyright (c) by 2019 Andreas Falk

[1]:http://www.apache.org/licenses/LICENSE-2.0.txt





