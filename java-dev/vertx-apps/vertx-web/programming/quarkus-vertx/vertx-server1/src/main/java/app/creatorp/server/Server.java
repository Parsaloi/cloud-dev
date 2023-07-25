package app.creatorp.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.web.Router;

/**
 * creatorP programming reactive programs :)
 */

public class Server extends AbstractVerticle  {

	public static void main(String[] args) {
		Launcher.executeCommand("run", Server.class.getName());
	}

	@Override
	public void start() throws Exception {

		Router router = Router.router(vertx);

		router.route().handler(routingContext -> {
			routingContext.response().putHeader("content-type", "text/html").end("Hello Reactive World!");
		});

		vertx.createHttpServer().requestHandler(router).listen(8080);
	}
}
