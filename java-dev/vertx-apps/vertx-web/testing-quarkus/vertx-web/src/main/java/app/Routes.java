package app;

import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.Route.HttpMethod;
import io.quarkus.vertx.web.RoutingExchange;
import io.vertx.ext.web.RoutingContext;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped // If there is a reactive route found on a class with no scope annotation then @jakarta.inject.Singleton is added automatically
public class MyDeclarativeRoutes {

	// neither path nor regex is set - match a path derived from the method name
	@Route(methods = Route.HttpMethod.GET) // The @Route annotation indicates that the method is a reactive route - Again, by default, the code
	// contained in the method must not block
	void hello(RoutingContext rc) { // The method gets a 'RoutingContext' as a parameter - From the RoutingContext you can retrieve the HTTP request
					// (using 'request()') and write the response using 'response().end(...)'
		rc.response().end("hello");
	}

	@Route(path = "/world")
	String helloWorld() { // If the annotated method does not return void the arguments are optional
		return "Hello reactive test world!";
	}

	@Route(path = "/greetings", methods = Route.HttpMethod.GET)
	void greetingQueryParam(RoutingExchange ex) { // RoutingExchage is a convinient wrapper of RoutingContext which provides some useful methods
		ex.ok("hello " + ex.getParam("name").orElse("reactive world")); // The RoutingExchange is used to retreive the request query parameter
										// name
	}

	@Route(path = "/greetings/:name", methods = Route.HttpMethod.GET) // The path defines a parameter name which can be injected inside the method
	// parameters using the annotation @Param
	void greetingPathParam(@Param String name, RoutingExchange ex) {
		ex.ok("hello " + name);
	}
}
