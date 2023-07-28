package app.creatorp.Login;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.temp.pebble.PebbleTemplateEngine;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception  {

    // Instanticate the Pebble template engine
    TemplateEngine engine = PebbleTemplateEngine.create(vertx);

    // ...with your new PebbleEngine instance you can start compiling templates:
    PebblTemplate compiledTemplate = engine.getTemplate("webroot/home.html");

    // Finaly, simply provide your compiled template with a java.io.Writer object and a Map of variables (the context) to get your output!
    // Write writer = new StringWriter();

    // Map<String, Object> context = new HashMap<>();
    // context.put("title", "Pebble web");
    // context.put("content", "Some content");

    // compiledTemplate evaluate(writer, context);

    // String.output = writer.toString();

    // Instantiate the template handler to replace the default handler
    TemplateHandler handler = TemplateHandler.create(engine);

    // This will route all GET requests starting with /dynamic/ to the template handler
    // E.g /dynamic/graph.hbs will look for a template in /templates/graph.hbs
    // router.get("/dynamic/").handler(handler);

    // Route all GET requests for resource ending in .hbs to the template handler
    // router.getWithRegex(".+\\.hbs").handler(handler);
    
    /**
     * To use Pebble, you need to add the following dependency to your project
     * io.vertx:vertx-web-templ:4.4.4 Create an instance of the Pebble template engine using
     * io.vertx.ext.web.templ.pebble.PebbleTemplateEngine#create(vertx)
     *
     * When using the Pebble template engine, it will by default look for templates with the .peb extension if no
     * extension is specified in the file name
     *
     * The routing context RoutingContext is available in the Pebble template :) as the context variable
     * this means you can render the template based on anything in the context including the request, response, session
     * or context data
     *
     */
	
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    }).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
