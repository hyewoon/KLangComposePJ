plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.hye.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 23

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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    //firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore.ktx)

    //mlkit digital ink recognition
    implementation(libs.digital.ink.recognition)

    //okhttp3
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)

    //dataStore preferences
    implementation(libs.androidx.datastore.preferences)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.bom)

    //tikxml
    implementation(libs.core)
    implementation(libs.annotation)
    implementation(libs.retrofit.converter)
    implementation(libs.processor)
    kapt(libs.core)

    //room
    implementation(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    //jetBrain annotations for(kapt & ksp)
    implementation(libs.annotations)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}