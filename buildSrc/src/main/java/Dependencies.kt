/**
 * Versions for libraries.
 */
object LibraryVersions {
    const val coroutines = "1.3.8"
    const val appcompat = "1.2.0"
    const val constraintlayout = "1.1.3"
    const val recyclerview = "1.1.0"
    const val material = "1.2.0"
    const val lifecycle = "2.2.0"
    const val arch = "2.1.0"
    const val retrofit = "2.9.0"
    const val okhttp = "4.8.1"
    const val glide = "4.11.0"
    const val junit = "4.13"
    const val mockk = "1.10.0"
    const val kotest = "4.1.2"
    const val androidxTest = "1.2.0"
}

/**
 * Versions for build plugins.
 */
object BuildVersions {
    const val agp = "4.0.1"
    const val kotlin = "1.4.0"
    const val androidLibrary = "7.1.3"
}

/**
 * Versions for build plugins.
 */
object BuildPlugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinAndroidExtensions = "org.jetbrains.kotlin.android.extensions"
    const val kotlinKapt = "org.jetbrains.kotlin.kapt"
    const val androidLibrary = "com.android.library"
}

object Libraries {
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${LibraryVersions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${LibraryVersions.coroutines}"
    const val appcompat = "androidx.appcompat:appcompat:${LibraryVersions.appcompat}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${LibraryVersions.constraintlayout}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${LibraryVersions.recyclerview}"
    const val material = "com.google.android.material:material:${LibraryVersions.material}"
    const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibraryVersions.lifecycle}"
    const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${LibraryVersions.lifecycle}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${LibraryVersions.lifecycle}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${LibraryVersions.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${LibraryVersions.retrofit}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${LibraryVersions.okhttp}"
    const val glide = "com.github.bumptech.glide:glide:${LibraryVersions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${LibraryVersions.glide}"
}

object TestLibs {
    const val junit = "junit:junit:${LibraryVersions.junit}"
    const val mockk = "io.mockk:mockk:${LibraryVersions.mockk}"
    const val kotestAssertions = "io.kotest:kotest-assertions-core-jvm:${LibraryVersions.kotest}"
    const val androidxTest = "androidx.test:core:${LibraryVersions.androidxTest}"
    const val archTest = "androidx.arch.core:core-testing:${LibraryVersions.arch}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${LibraryVersions.coroutines}"
}
