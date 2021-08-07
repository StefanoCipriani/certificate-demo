# SSL Demo Project:

## Endpoint

https://localhost:8444/ping

This url use the key store  demo-ssl\src\main\resources\keystore and configured in demo-ssl\src\main\resources\application.properties

https://localhost:8444/tacos
This url use the trust-store demo-ssl\src\main\resources\taco-trust-store configured via the VM arguments 

-Djavax.net.ssl.trustStore=path-to-taco-trust-store
-Djavax.net.ssl.trustStorePassword=P@ssw0rd

and call the other service listenion at https://localhost:8443/tacos

Run bot projects to try how it works