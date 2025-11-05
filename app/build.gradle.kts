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
        minSdk = 24
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
    implementation("androidx.compose.foundation:foundation:1.5.4")

    // Navigation component for Jetpack Compose
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // Lifecycle and ViewModel components for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    // Material Design icons extended library
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    // Retrofit for network operations
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Kotlin Coroutines for asynchronous operations
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // OkHttp logging interceptor for debugging network calls
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Firebase Bill of Materials (BOM) - UPDATE TO LATEST VERSION
    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))

    // Firebase Authentication - for user authentication
    implementation("com.google.firebase:firebase-auth-ktx")

    // Firebase Firestore - for cloud database
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Google Play Services - ADD BASE DEPENDENCY
    implementation("com.google.android.gms:play-services-base:18.5.0")

    // Google Play Services Auth - required by Firebase Authentication
    implementation("com.google.android.gms:play-services-auth:21.2.0")

    implementation(libs.firebase.firestore)
    implementation(libs.firebase.ai)

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