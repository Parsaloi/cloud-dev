package org.acme;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * A note from, the 'my-service' part in the 'stork://my-service' URL on the 'MyService.java' file
 * This does not change how the REST client is used
 *
 * Now we create the FrontEndApi program file
 *
 * A frontend API using our REST Client (which uses Stork to locate and select the service instance on each call)
 * It injects and uses the REST client as usual
 */
@Path("/api")
public class FrontEndApi {

	@RestClient MyService service;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String invoke() {
		return service.get();
	}
}
