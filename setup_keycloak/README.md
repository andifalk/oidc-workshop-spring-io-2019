# Preparation: Setting up Keycloak as Identity Provider

In this workshop we will use [Keycloak](https://keycloak.org) by JBoss/RedHat as local identity provider.

[Keycloak](https://keycloak.org) is certified for OpenID Connect 1.0 and OAuth 2.0 and therefore supports all
hands-on labs of this workshop.

## Setup Keycloak

To setup [Keycloak](https://keycloak.org): 

1. Download the binary distribution [here (zip archive)](https://downloads.jboss.org/keycloak/6.0.1/keycloak-6.0.1.zip).
2. Extract the downloaded zip file __keycloak-6.0.1.zip__ into a new local directory of your choice 
(this directory will be referenced as __<KEYCLOAK_INSTALL_DIR>__ in next steps)
3. Download the prepared keycloak configuration for this 
workshop [here](https://github.com/andifalk/oidc-workshop-spring-io-2019/raw/master/setup_keycloak/keycloak_data.zip)
4. Extract the downloaded file __keycloak_data.zip__ into the sub directory __<KEYCLOAK_INSTALL_DIR>/standalone__ 
that has been created as part of step 2
5. Open a terminal and change directory to sub directory __<KEYCLOAK_INSTALL_DIR>/bin__ and start Keycloak using the __standalone.sh__ or __standalone.bat__ 
scripts depending on your operating system used
6. Wait until keycloak has been started completely - you should see something like this `6.0.1 (WildFly Core 8.0.0.Final) started in 6902ms - Started 580 of 842 services`
7. Now direct your browser to [localhost:8080/auth/admin](http://localhost:8080/auth/admin/)
8. Login into the admin console using __admin/admin__ as credentials

Now, if you see the realm _workshop_ on the left then Keycloak is ready to use it for this workshop

![Keycloak Workshop](keycloak_workshop.png)

If you want to know more about setting up a Keycloak server for your own projects 
then please consult the [keycloak administration docs](https://www.keycloak.org/docs/latest/server_admin/index.html).