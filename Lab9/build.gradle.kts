plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    implementation ("org.eclipse.persistence:javax.persistence:2.2.1")
    implementation ("org.eclipse.persistence:eclipselink:2.7.7")
    implementation ("mysql:mysql-connector-java:8.0.26")
}

tasks.test {
    useJUnitPlatform()
}