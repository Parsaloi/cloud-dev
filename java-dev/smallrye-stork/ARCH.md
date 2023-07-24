
# Architecture
> <https://quarkus.io/guides/stork>

In this guide, we will build an application composed of:  

- A simple blue service exposed on port `9000`  
- A simple red service exposed on port `9001`  
- A REST Client calling the blue or red service (the selection is delegated to Stork)  
- A REST endpoint using the REST client and calling the services  
- The blue and red services are registered in Consul (open source service discovery) <https://www.consul.io/>


