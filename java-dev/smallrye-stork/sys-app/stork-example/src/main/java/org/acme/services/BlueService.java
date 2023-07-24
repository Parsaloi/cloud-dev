package org.acme.services;

import io.quarkus.runtime.StartupEvent;
import io.vertex.mutiny.core.Vertx;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

/**
 * First, I create the service that we will be discovered, select and call
 * Here, we create a new HTTP server (using Vertx) and implements our simple service when the application starts
 * For each HTTP request, it sends a response with "Hello from Blue" as the body
 */

@ApplicationScoped
public class BlueService {

	@ConfigProperty(name = "blue-service-port", defaultValue = "9000") int port;

	/**
	 * Start an HTTP server for the blue service
	 *
	 * Note: this method is called on a worker thread, and so it is allowed to block
	 */
	public void init(@Observes StartupEvent ev, Vertx vertx) {
		vertx.createHttpServer()
			.requestHandler(req -> req.response().endAndForget("Hello from Blue!"))
			.listenAndAwait(port);
	}
}
