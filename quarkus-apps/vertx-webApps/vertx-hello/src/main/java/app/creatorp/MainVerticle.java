package app.creatorp;

import io.vertx.core.AbstractVerticle;

public class MainVerticle extends AbstractVerticle {
	
	public void start() {
		vertx.createHttpServer().requestHandler(req -> {
			req.response()
				.putHeader("content-type", "text/plain")
				.end("Hello from Vert.x!");
		}).listen(8081);
	}
}
