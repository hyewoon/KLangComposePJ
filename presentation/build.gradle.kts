plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlinx.serialization)

}

android {
    namespace = "com.hye.presentation"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
       // consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {

    // domain 모듈만 의존
    implementation(project(":domain"))

    //Android 기본
    implementation(libs.androidx.core.ktx)

    // Compose UI bundle
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    implementation(libs.androidx.activity.compose)

    //Navigation
    implementation(libs.androidx.navigation.compose)

    //Lifecycle
    implementation(libs.bundles.lifecycle)

    // Hilt
    implementation(libs.bundles.hilt)
    implementation(libs.mockk)
    implementation(libs.kotlinx.coroutines.test)
    ksp(libs.hilt.android.compiler)

    //Utilities
    implementation(libs.bundles.utils)

    //permission
    implementation(libs.bundles.permission)

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Test
    testImplementation(libs.bundles.testing)

    //Android Test
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.compose.testing)

   //디버그용
    debugImplementation(libs.bundles.compose.tooling)

}