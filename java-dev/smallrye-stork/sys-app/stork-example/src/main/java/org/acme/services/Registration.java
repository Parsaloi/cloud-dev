package org.acme.services;

import io.quarkus.runtime.StartupEvent;
import io.vertx.ext.consul.ServiceOptions;
import io.vertx.mutiny.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.mutiny.core.Vertx;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

/**
 * After creating the two services 'blue-svc, red-svc' services, I need to register them into Consul
 * When the application starts, it connects to Consul using the Vert.x Consul and registers our two instaces
 * Both registrations uses the same name 'my-service', but different ids to indicate that it's two instances of the same service
 */

@ApplicationScoped
public class Registration {

	@ConfigProperty(name = "consul.host") String host;
	@ConfigProperty(name = "consul.port") int port;

	@ConfigProperty(name = "red-service-port", defaultValue = "9000") int red;
	@ConfigProperty(name = "blue-service-port", defaultValue = "9001") int blue;

	/**
	 * Register our two service in Consul
	 *
	 * Note: this method is called on a worker thread, and so it is allowed to block
	 */
	public void init(@Observes StartupEvent ev, Vertx vertx) {
		ConsulClient client = ConsulClient.create(vertx, new ConsulClientOptions().setHost(host).setPort(port));

		client.registerServiceAndAwait(
				new ServiceOptions().setPort(red).setAddress("localhost").setName("my-service").setId("red"));
		client.registerServiceAndAwait(
				new ServiceOptions().setPort(red).setAddress("localhost").setName("my-service").setId("blue"));
	}
}
