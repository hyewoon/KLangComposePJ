plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt.plugin)
}

android {
    namespace = "com.hye.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        //var jvmTarget = "17"
    }

    /*   kotlinOptions {
           jvmTarget = "17"
       }*/

    kotlin {
        jvmToolchain(17)
    }
}


dependencies {

    implementation(project(":domain"))

    //android 기본
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    //firebase bundle
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)

    //mlkit digital ink recognition
    implementation(libs.digital.ink.recognition)

    //networking bundle
    implementation(libs.bundles.networking)

    //dataStore preferences
    implementation(libs.androidx.datastore.preferences)

    //tikxml bundle
    implementation(libs.bundles.tikxml)
    kapt(libs.tikxml.processor)

    //room bundle
   implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)

    //hilt
    implementation(libs.bundles.hilt)
    ksp(libs.hilt.android.compiler)

    //jetBrain annotations for(kapt & ksp)
    implementation(libs.annotations)

    //test
    testImplementation(libs.bundles.testing)

}