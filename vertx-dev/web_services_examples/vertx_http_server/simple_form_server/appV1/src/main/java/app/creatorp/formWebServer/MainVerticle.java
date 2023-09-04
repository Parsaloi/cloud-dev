package app.creatorp.formWebServer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {


		// Create server and Bind router
		vertx.createHttpServer().requestHandler(router)
			.listen(8084, http -> {
				startPromise.complete();
				System.out.println("HTTP Server started on port 8084");
			} else {
				startPromise.fail(http.cause());
			}
	});
}
