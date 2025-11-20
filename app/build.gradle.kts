plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.nusatrip"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.nusatrip"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Core Android libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Jetpack Compose BOM and UI components
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Foundation for HorizontalPager
    implementation(libs.androidx.compose.foundation)

    // Material Design icons extended library
    implementation(libs.androidx.compose.material.icons.extended)

    // Navigation component for Jetpack Compose
    implementation(libs.androidx.navigation.compose)

    // Lifecycle and ViewModel components for Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Retrofit for network operations
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // OkHttp logging interceptor for debugging network calls
    implementation(libs.logging.interceptor)

    // Firebase Dependencies
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.ai)
    implementation(libs.firebase.firestore.ktx)
    implementation(platform(libs.firebase.bom))

    // Google Play Services
    implementation(libs.play.services.base)
    implementation(libs.play.services.auth)

    // Kotlin Coroutines for asynchronous operations
    implementation(libs.kotlinx.coroutines.android)

    // Accompanist libraries for pager
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.ui.unit)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Testing libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

// Apply Google Services plugin - MUST be at the bottom
apply(plugin = "com.google.gms.google-services")