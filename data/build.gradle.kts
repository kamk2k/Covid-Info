plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Dependencies.Android.compileSdkVersion)
    buildToolsVersion(Dependencies.Android.buildToolsVersion)
    testOptions.unitTests.isIncludeAndroidResources = true

    defaultConfig {
        minSdkVersion(Dependencies.Android.minSdkVersion)
        targetSdkVersion(Dependencies.Android.targetSdkVersion)
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            consumerProguardFiles("consumer-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions.apply {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

}

dependencies {
    api(project(":domain"))
    api(Dependencies.Libs.kotlinStd)
    api(Dependencies.Libs.ktx)
    implementation(Dependencies.Libs.kotlinxCoroutines)
    implementation(Dependencies.Libs.moshi)
    implementation(Dependencies.Libs.jodaTime)
    implementation(Dependencies.Libs.room)
    implementation(Dependencies.Libs.roomKtx)
    kapt(Dependencies.Libs.roomCompiler)
    testImplementation(Dependencies.TestLibs.jUnit)
    testImplementation(Dependencies.TestLibs.assertj)
    testImplementation(Dependencies.TestLibs.mockito)
    testImplementation(Dependencies.TestLibs.koinTest)
    testImplementation(Dependencies.TestLibs.kotlinxCoroutinesTest)
    androidTestImplementation(Dependencies.TestLibs.espresso)
}
