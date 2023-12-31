/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * To learn more about Gradle by exploring our Samples at https://docs.gradle.org/8.2.1/samples
 */
plugins {
	java
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("io.vertx:vertx-core:4.4.4")
	implementation("io.verts:vertx-web:4.4.4")
}

tasks.create<JavaExec>("run") {
	main = project.properties.getOrDefault("mainClass", "MainVerticle") as String
	classpath = sourceSets["main"].runtimeClasspath
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
}
