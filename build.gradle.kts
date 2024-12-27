// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    // Ksp
    id ("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
    // Hilt
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    // Google SignIn
   id("com.google.gms.google-services") version "4.4.2" apply false
    // Google Maps
   // id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false

}