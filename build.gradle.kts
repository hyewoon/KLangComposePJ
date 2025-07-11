// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.hilt.plugin) apply false

}


subprojects {
    configurations.all {
        resolutionStrategy {
            force("org.jetbrains:annotations:26.0.2")
        }
    }


}

