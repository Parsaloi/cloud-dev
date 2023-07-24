package org.acme;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * So far, I have just scaffolded the services that will be discovered, selecting, and calling
 * We will call the services using the Reactive REST Client
 * 
 * The REST Client interface
 * Notice the 'baseUri' It uses 'stork://' as URL scheme indicating that the called service uses Stork to locate and select the service istance 
 * The 'my-service' part is the service name -- This is used to configure Stork discovery and selection in the 'application.properties' file
 *
 * It's a straightforward REST client interface containing a single method
 * However, note the 'baseUri' attribute 
 * It starts with 'stork://'
 * It instructs the REST client to delegate the discovery and selection of the service instances to Stork
 * Notice the 'my-service' part in the URL
 * It is the service name we will be using in the 'application configuration'
 */
@RegisterRestClient(baseUri = "stork://my-service")
public interface MyService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	String get();
}
