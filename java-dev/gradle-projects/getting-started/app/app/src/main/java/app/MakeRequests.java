package app;

/**
 * The public API service forwards requests to the 'user profile' and 'activity' services
 * We need to use the Vertx web client to make HTTP requests
 * The 'WebClient' class from the 'vertx-web-client' module offers a richer API
 *
 * A 'WebClient' instance is typically stored in a private field of a 'vertical class', as it can be
 */

import io.vertx.ext.web.client;

public MakeRequests {

	// Creating a web client instance is a simple as this:
	WebClient webclient = WebClient.create(vertx);
}
