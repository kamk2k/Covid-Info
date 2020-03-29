plugins {
    id("com.android.application")
    kotlin("android")
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

    api(Dependencies.Libs.ktx)
    api(Dependencies.Libs.appcompat)
    api(Dependencies.Libs.constraintLayout)
    testImplementation(Dependencies.TestLibs.jUnit)
    androidTestImplementation(Dependencies.TestLibs.androidXJUnit)
    androidTestImplementation(Dependencies.TestLibs.espresso)
}