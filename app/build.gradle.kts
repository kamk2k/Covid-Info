plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Dependencies.Android.compileSdkVersion)

    defaultConfig {
        applicationId = "com.corellidev.covidinfo"
        minSdkVersion(Dependencies.Android.minSdkVersion)
        targetSdkVersion(Dependencies.Android.targetSdkVersion)
        versionCode = Dependencies.Android.versionCode
        versionName = Dependencies.Android.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Dependencies.BuildPlugins.kotlinGradlePlugin)
    implementation(Dependencies.Libs.ktx)
    implementation(Dependencies.Libs.appcompat)
    implementation(Dependencies.Libs.constraintLayout)
    testImplementation(Dependencies.TestLibs.jUnit)
    androidTestImplementation(Dependencies.TestLibs.androidXJUnit)
    androidTestImplementation(Dependencies.TestLibs.espresso)
}