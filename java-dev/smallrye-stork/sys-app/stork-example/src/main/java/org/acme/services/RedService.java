package org.acme.services;

import io.quarkus.runtime.StartupEvent;
import io.vertx.mutiny.core.Vertx;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

/**
 * Following the same logic from 'BlueService', create the 'RedService'
 * This time, it writes "Hello from Red!"
 */

@ApplicationScoped
public class RedService {
	@COnfigProperty(name = "red-service-port", defaultValue = "9001") int port;

	/**
	 * Start an HTTP server for the red service
	 *
	 * Note: this method is called on a worker thread, and so it is allowed to block
	 */
	public void init(@Observes StartupEvent ev, Vertx vertx) {
		vertx.createHttpServer()
			.requestHandler(req -> req.response().endAndForget("Hello from Red!"))
			.listenAndAwait(port);
	}
}
