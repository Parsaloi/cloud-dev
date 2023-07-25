
## Vertx Web
> <https://vertx.io/docs/vertx-web/java/>

- Here, we create an HTTP server instance, and we set a request handler on it   
- The request handler will be called whenever a request arrives on the server  
- When that happens we are just going to set the context type to `text/plain`, and write `Hello Reactive World!` and end the response
- We then tell the server to listen at port `8080` (default host is `localhost`)

You can run this, and point your browser at `http://localhost:8080` to verify that it works as expected
