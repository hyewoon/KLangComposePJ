import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.services)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.hye.klangcomposepj"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.hye.klangcomposepj"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        packaging {
            resources {
                excludes += "META-INF/gradle/incremental.annotation.processors"
            }
        }

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "API_KEY", "\"${properties.getProperty("api.key")}\"")
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
    }

    // 🔧 JUnit 5 충돌 해결을 위한 packaging 블록 추가
    packaging {
        resources {
            excludes += setOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt"
            )
        }
    }

}


dependencies {

    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))

    //hilt bundle
    implementation(libs.bundles.hilt)
    ksp(libs.hilt.android.compiler)

    //testing
    testImplementation(libs.bundles.testing)



}

