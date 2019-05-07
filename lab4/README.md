# Lab 3: Creating an OAuth 2.0/OIDC compliant Client


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





