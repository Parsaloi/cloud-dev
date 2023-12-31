/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package hello.app;

import io.smallrye.mutiny.Uni;

public class App {

    public static void main(String[] args) {
	    Uni.createFrom().item("hello") // Create from some library method...
		    .onItem().transform(item -> item + " mutiny") // transform reactive item by concatinating string item
		    .onItem().transform(String::toUpperCase) // transform reactive item using method references
		    .subscribe().with(item -> System.out.println(">> " + item)); // subscribe to reactive item and print it*
    }
}
