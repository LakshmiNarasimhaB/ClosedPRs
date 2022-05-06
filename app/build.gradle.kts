import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
}

android {

    compileSdkVersion = Sdk.compileVersion

    defaultConfig {
        minSdk = Sdk.minVersion
        targetSdk = Sdk.targetVersion

        applicationId = App.id
        versionCode = App.versionCode
        versionName = App.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "API_KEY",
            gradleLocalProperties(rootDir).getProperty("GIT_HUB_API_KEY")
        )

        buildConfigField(
            "String",
            "OWNER",
            gradleLocalProperties(rootDir).getProperty("GIT_HUB_OWNER")
        )

        buildConfigField(
            "String",
            "REPOSITORY",
            gradleLocalProperties(rootDir).getProperty("GIT_HUB_REPOSITORY")
        )
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Coroutines
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinesAndroid)
    // UI and Appcompat
    implementation(Libraries.appcompat)
    implementation(Libraries.constraintlayout)
    implementation(Libraries.recyclerview)
    implementation(Libraries.material)
    implementation(Libraries.swiperefreshlayout)
    // ViewModel and LiveData
    implementation(Libraries.viewmodelKtx)
    implementation(Libraries.livedataKtx)
    implementation(Libraries.lifecycleRuntimeKtx)

    // Retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitConverterGson)
    implementation(Libraries.loggingInterceptor)

    // Glide
    implementation(Libraries.glide)
    kapt(Libraries.glideCompiler)

    // Unit Tests
    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.mockk)
    testImplementation(TestLibs.kotestAssertions)
    testImplementation(TestLibs.androidxTest)
    testImplementation(TestLibs.archTest)
    testImplementation(TestLibs.coroutinesTest)
}
