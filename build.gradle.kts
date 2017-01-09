import org.gradle.api.tasks.JavaExec
import org.gradle.jvm.tasks.Jar

group = "katas"

buildscript {
    extra["junitPlatformVersion"] = "1.0.0-M3"
    extra["junitJupiterVersion"] = "5.0.0-M3"
    extra["junitVintageVersion"] = "4.12.0-M3"
    extra["assertjVersion"] = "3.5.2"

    repositories {
        mavenLocal()
        jcenter()
        gradleScriptKotlin()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin"))
        classpath("org.junit.platform:junit-platform-gradle-plugin:${extra["junitPlatformVersion"]}")
    }
}

apply {
    plugin("java")
    plugin("kotlin")
    plugin("idea")
    plugin("org.junit.platform.gradle.plugin")
}

configure<JavaPluginConvention> {
    setSourceCompatibility(1.8)
    setTargetCompatibility(1.8)
}

repositories {
    mavenLocal()
    gradleScriptKotlin()
    jcenter()
    mavenCentral()
}

dependencies {
    compile(kotlinModule("stdlib"))

    // Test dependencies

    testCompile(gradleTestKit())

    // JUnit Jupiter API
    testCompile("org.junit.jupiter:junit-jupiter-api:${extra["junitJupiterVersion"]}")
    testCompile("org.junit.platform:junit-platform-runner:${extra["junitPlatformVersion"]}")

    // TestEngine implementations (only needed at runtime)
    testRuntime("org.junit.jupiter:junit-jupiter-engine:${extra["junitJupiterVersion"]}")    //JUnit5
    //testRuntime("org.junit.vintage:junit-vintage-engine:${extra["junitVintageVersion"]}")    //JUnit4

    testCompile("org.assertj:assertj-core:${extra["assertjVersion"]}")
}
