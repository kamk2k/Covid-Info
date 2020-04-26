plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("androidx.navigation.safeargs.kotlin")
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

    api(project(":data"))
    api(Dependencies.Libs.ktx)
    api(Dependencies.Libs.appcompat)
    api(Dependencies.Libs.constraintLayout)
    implementation(Dependencies.Libs.koinCore)
    implementation(Dependencies.Libs.koinViewModel)
    implementation(Dependencies.Libs.koinScope)
    implementation(Dependencies.Libs.navigationFragment)
    implementation(Dependencies.Libs.navigationUI)
    implementation(Dependencies.Libs.navigationDynamicFeatures)
    implementation(Dependencies.Libs.androidXLegacy)
    implementation(Dependencies.Libs.liveData)
    implementation(Dependencies.Libs.viewModel)
    implementation(Dependencies.Libs.recyclerView)
    implementation(Dependencies.Libs.jodaTime)
    implementation(Dependencies.Libs.room)
    implementation(Dependencies.Libs.roomKtx)
    testImplementation(Dependencies.TestLibs.jUnit)
    testImplementation(Dependencies.TestLibs.assertj)
    testImplementation(Dependencies.TestLibs.mockito)
    testImplementation(Dependencies.TestLibs.koinTest)
    testImplementation(Dependencies.TestLibs.kotlinxCoroutinesTest)
    androidTestImplementation(Dependencies.TestLibs.androidXJUnit)
    androidTestImplementation(Dependencies.TestLibs.espresso)
    androidTestImplementation(Dependencies.TestLibs.navigationTesting)
}