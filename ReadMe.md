# SSL Demo Project:

## Endpoint

https://localhost:8444/ping

This url use the key store  demo-ssl\src\main\resources\keystore configured in demo-ssl\src\main\resources\application.properties
Shows that the https protocol is correctly set

https://localhost:8444/tacos

This url use the trust-store demo-ssl\src\main\resources\taco-trust-store configured via the VM arguments 
Shows how to call an external https service

-Djavax.net.ssl.trustStore=path-to-taco-trust-store
-Djavax.net.ssl.trustStorePassword=P@ssw0rd

and call the other service listen on at https://localhost:8443/tacos

Same as previous url, the difference is that this one use the trust-store configured in the application.properties file
https://localhost:8444/tacosInternalTrustStore

Run both projects to try how it works

