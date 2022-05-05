// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id (BuildPlugins.androidApplication) version BuildVersions.agp apply false
    id(BuildPlugins.androidLibrary) version BuildVersions.androidLibrary apply false
    id(BuildPlugins.kotlinAndroid) version BuildVersions.kotlin apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}
