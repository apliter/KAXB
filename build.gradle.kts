import org.gradle.jvm.tasks.Jar

/*
 *    Copyright 2017 SixRQ Ltd.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

group = "com.sixrq"
version = "1.0-SNAPSHOT"

buildscript {
    repositories {
        gradleScriptKotlin()
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.0")
        classpath(kotlinModule("gradle-plugin"))
        classpath("com.github.jengelman.gradle.plugins:shadow:1.2.4")
    }
}

plugins {
    application
    groovy
    java
}

apply {
    plugin("kotlin")
    plugin("com.github.johnrengelman.shadow")
}

repositories {
    gradleScriptKotlin()
    jcenter()
}

dependencies {
    compile(kotlin("stdlib"))
    compile("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.0")
    compile("org.codehaus.groovy:groovy-all:2.3.11")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.2")
    testCompile("org.spockframework:spock-core:1.0-groovy-2.4")
    testCompile("junit:junit:4.11")
}

configure<ApplicationPluginConvention> {
    mainClassName = "com.sixrq.kaxb.main.SchemaGenerator"
}

val jar: Jar by tasks
jar.apply {
    manifest.attributes.apply {
        put("Main-Class", "com.sixrq.kaxb.main.SchemaGenerator")
    }
}