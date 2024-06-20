


plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.teachandlearn"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.teachandlearn"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    dependencies {
        // Import the BoM for the Firebase platform
        implementation(platform(libs.firebase.bom))

        // Add the dependency for the Cloud Storage library
        // When using the BoM, you don't specify versions in Firebase library dependencies
        implementation(libs.google.firebase.storage)
        implementation (libs.gemini.sdk)
    }
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation (libs.play.services.mlkit.text.recognition)
    implementation(libs.generativeai)
    implementation (libs.gemini)

    // Required for one-shot operations (to use `ListenableFuture` from Guava Android)
    implementation(libs.guava)

    // Required for streaming operations (to use `Publisher` from Reactive Streams)
    implementation(libs.reactive.streams)
    implementation (libs.generativeai.sdk)


    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.appcompat)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.auth)
    implementation(libs.play.services.auth)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.google.firebase.database)
    implementation(libs.firebase.firestore)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.volley)
    implementation(libs.material)
    implementation(libs.junit)
    implementation(libs.ext.junit)
    implementation(libs.espresso.core)
    implementation(libs.vision.common)
    implementation(libs.play.services.mlkit.text.recognition.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)




}