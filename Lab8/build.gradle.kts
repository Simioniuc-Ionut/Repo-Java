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
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("mysql:mysql-connector-java:8.0.26")
    implementation ("org.hibernate:hibernate-core:5.4.27.Final")
}
dependencies {
    implementation ("com.opencsv:opencsv:5.5.2")
    implementation ("org.apache.commons:commons-dbcp2:2.12.0")
    implementation ("org.jgrapht:jgrapht-core:1.5.1")
}

tasks.test {
    useJUnitPlatform()
}