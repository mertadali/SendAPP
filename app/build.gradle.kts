plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    // id("com.google.gms.google-services")
   // id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")


}

android {
    namespace = "com.mertadali.sendapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mertadali.sendapp"
        minSdk = 24
        targetSdk = 35
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
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



    //   --------- HILT----------
    // ->  https://developer.android.com/build/migrate-to-ksp,     https://developer.android.com/training/dependency-injection/hilt-android
    val hiltVersion = "2.51.1"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    ksp ("com.google.dagger:hilt-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("androidx.hilt:hilt-work:1.2.0")
    implementation("androidx.work:work-runtime-ktx:2.10.0")

    // --------- Jetpack Compose---------------
    // -> https://developer.android.com/develop/ui/compose/setup
    val composeUiVersion = "1.7.5"
    implementation ("androidx.compose.material:material-icons-core:$composeUiVersion")
    implementation ("androidx.compose.material:material-icons-extended:$composeUiVersion")
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.1.0")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation ("androidx.navigation:navigation-compose:2.8.4")
    implementation ("com.google.accompanist:accompanist-flowlayout:0.28.0")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")


    // Coil - Glide, Picasso gibi ancak compose destekli görsel indirmek için.
    implementation ("io.coil-kt:coil-compose:2.4.0")


    // ---- ROOM DATABASE------  -> https://developer.android.com/jetpack/androidx/releases/room
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")     // ->  optional - Kotlin Extensions and Coroutines support for Room




    // TestImplementations
    implementation ("androidx.test:core:1.6.1")
    testImplementation ("org.hamcrest:hamcrest-all:1.3")
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    testImplementation ("org.robolectric:robolectric:4.8.1")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
    testImplementation ("com.google.truth:truth:1.4.4")
    testImplementation ("org.mockito:mockito-core:4.7.0")
    testImplementation("com.google.firebase:firebase-auth:23.1.0")


    // Android Test Implementations
    androidTestImplementation ("org.mockito:mockito-android:4.7.0")
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation ("com.google.truth:truth:1.4.4")
    androidTestImplementation ("org.mockito:mockito-core:4.7.0")
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.51.1")
    kspAndroidTest ("com.google.dagger:hilt-android-compiler:2.51.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.7.5")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.7.5")
    androidTestImplementation("com.google.firebase:firebase-messaging:24.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.6.1")


    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage")
    implementation("com.google.firebase:firebase-messaging-ktx")

    //Auth
    implementation("com.google.firebase:firebase-auth")
    // Google SignIn and Maps
    //implementation("com.google.android.gms:play-services-auth:21.2.0")
    //implementation ("com.google.android.gms:play-services-maps:19.0.0")




}