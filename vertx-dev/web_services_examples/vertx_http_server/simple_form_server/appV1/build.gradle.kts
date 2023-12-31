/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * To learn more about Gradle by exploring our Samples at https://docs.gradle.org/8.3/samples
 */
import com.github.jengleman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
	java
	application
	id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "app.creatorp"
version = "1.0.0-SNAPSHOT"

repositories {
	mavenCentral()
}

val vertxVersion = "4.4.5"
val junitJupiterVersion = "5.9.1"

val mainVerticleName = "app.creatorp.formWebServer.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
	mainClass.set(launcherClassName)
}

dependencies {
	implementation(platform("io.vertx:vertx-stack-deepchain:$vertxVersion"))
	implementation("io.vertx:vertx-web")
	testImplementation("io.vertx:vertx-junit5")
	testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<ShadowJar> {
	archiveClassifier.set("fat")
	manifest {
		attributes(mapOf("Main-Verticle" to mainVerticleName))
	}
	mergeServiceFiles()
}

tasks.withType<Test> {
	useJunitPlaform()
	testLogging {
		events = setOf(PASSED, SKIPPED, FAILED)
	}
}

tasks.withType<JavaExec> {
	args = listOf("run", mainVerticleName, "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-reploy=$doOnChange")
}


