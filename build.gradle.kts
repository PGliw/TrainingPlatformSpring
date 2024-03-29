import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.1.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	id("org.jetbrains.kotlin.plugin.allopen") version "1.3.61"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
	kotlin("plugin.jpa") version "1.3.50"
}

group = "pl.pchorosc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val developmentOnly by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
}

repositories {
	mavenCentral()
	// Only Spring RELEASE versions are published to Maven Central Repository, milestones are in milestone repo
	maven {  setUrl("https://repo.spring.io/milestone") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	// https://mvnrepository.com/artifact/org.modelmapper/modelmapper
	implementation("org.modelmapper:modelmapper:2.3.5")


	// https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-gcp-dependencies
	// implementation("org.springframework.cloud:spring-cloud-gcp-dependencies:1.0.0.RC1")

	// Google CloudSQL MySQL
	// implementation("org.springframework.cloud:spring-cloud-gcp-starter-sql-mysql:1.0.0.M2")

	// https://mvnrepository.com/artifact/org.springframework.security/spring-security-core
	//implementation("org.springframework.security:spring-security-core:5.2.1.RELEASE")
	//implementation("org.springframework.security:spring-security-config:5.2.1.RELEASE")

	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security
	implementation("org.springframework.boot:spring-boot-starter-security:2.2.2.RELEASE")

	// For embedding oauth server
	// https://mvnrepository.com/artifact/org.springframework.security.oauth/spring-security-oauth2
	implementation("org.springframework.security.oauth:spring-security-oauth2:2.4.0.RELEASE")

	// For using app as oauth client
	// https://mvnrepository.com/artifact/org.springframework.security.oauth.boot/spring-security-oauth2-autoconfigure
	implementation("org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure:2.2.2.RELEASE")




	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("mysql:mysql-connector-java")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
