plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.jetbrainsKotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)

    alias(libs.plugins.google.services)

}
hilt{
    enableAggregatingTask = false
}

android {
    namespace = "com.oso.myfirebase"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.oso.myfirebase"
        minSdk = 36
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //serialization json
    implementation(libs.kotlinx.serialization.json)

    //room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.navController)

    implementation(libs.icons)

    implementation(libs.dataPreferences)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter)

    //Coil
    implementation(libs.coil)

    //google services
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}