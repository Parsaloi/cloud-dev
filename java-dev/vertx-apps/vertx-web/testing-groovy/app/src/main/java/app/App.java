/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package app;

import io.vertx.core.http.*;
import io.vertx.web.*;
import io.vertx.web.handler.*;

public class App {

    public static void main(String[] args) {

            HttpServer server = vertx.createHttpServer();

            /**
             * -- Basic Vertx-Web concepts, 10000 foot view
             *  A 'Router' is one of the core concepts of Vertx-Web - It's an object which maintains zero or more 'Routes'
             *
             *  A router (1) will pause the incoming 'HttpServerRequest, to ensure that the request body or any protocol upgrades are not lost
             *  Second, it will find the first matching route for that request, and passes the request to that route
             *
             *  The route can have a handler associated with it, which then receives the request
             *  You then 'do something' with the request, and then, either end it or pass it to the next matching handler
             */

            Router router = Router.router(vertx);

            router.route().handler(ctx -> {
                    // This is the routing context

            // server.requestHandler(request -> {

                    // This handler gets called for each request that arrives on the server
                    // HttpServerResponse response = request.response();
                    HttpServerResponse response = ctx.response();
                    response.putHeader("content-type", "text/plain");

                    // Write to the response and end it
                    response.end("Hello Reactive World from Vert.x-Web!");
            });

            // server.listen(8080);
            server.requestHandler(router).listen(8080);

            /**
             * Router basically does the same thing as the Vertx Core Http server example, but this time using Vert.x-Web
             *
             * We create an HTTP server as before, then we create a router - Once we've done that we create a simple route with
             * no matching criteria so it will match all requests that arrive on the server
             *
             * We then specify a handler for that route -- That handler will be called for all requests that arrive on the server
             *
             * The object that gets passed into the handler is a 'RoutingContext' - this contains the standard Vert.x 'HttpServerRequest'
             * and 'HttpServerResponse' but also various other useful stuff that makes working with Vert.x-Web simpler
             *
             * For every request that is routed there is a unique routing context instance, and the same instance is passed to all handlers
             * for that request
             *
             * Once we've setup the handler, we set the request handler to the Http server to pass all incoming requests to 'handle'
             */
    }
}
