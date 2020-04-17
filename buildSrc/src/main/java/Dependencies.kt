private const val kotlinVersion = "1.3.71"
private const val androidGradleVersion = "4.1.0-alpha04"

private const val ktxVersion = "1.2.0"
private const val appcompatVersion = "1.1.0"
private const val constraintLayoutVersion = "1.1.3"
private const val koinVersion = "2.1.5"
private const val navigationVersion = "2.3.0-alpha04"
private const val androidXLegacyVersion = "1.0.0"
private const val lifecycleVersion = "2.3.0-alpha01"
private const val recyclerViewVersion = "1.1.0"
private const val moshiVersion = "1.9.2"
private const val kotlinxCoroutinesVerision = "1.3.5"

private const val jUnitVersion = "4.12"
private const val androidXJUnitVersion = "1.1.1"
private const val espressoVersion = "3.2.0"
private const val mockitoVersion = "1.10.19"

class Dependencies {
    object BuildPlugins {
        const val androidGradle = "com.android.tools.build:gradle:$androidGradleVersion"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion"
    }

    object Android {
        const val buildToolsVersion = "29.0.2"
        const val minSdkVersion = 26
        const val targetSdkVersion = 29
        const val compileSdkVersion = 29
        const val versionCode = 1
        const val versionName = "0.1"
    }

    object Libs {
        const val ktx = "androidx.core:core-ktx:$ktxVersion"
        const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        const val koinCore = "org.koin:koin-core:$koinVersion"
        const val koinViewModel = "org.koin:koin-androidx-viewmodel:$koinVersion"
        const val koinScope = "org.koin:koin-androidx-scope:$koinVersion"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
        const val navigationDynamicFeatures = "androidx.navigation:navigation-dynamic-features-fragment:$navigationVersion"
        const val androidXLegacy = "androidx.legacy:legacy-support-v4:$androidXLegacyVersion"
        const val recyclerView = "androidx.recyclerview:recyclerview:$recyclerViewVersion"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val moshi = "com.squareup.moshi:moshi:$moshiVersion"
        const val kotlinx_coroutines =  "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVerision"
    }

    object TestLibs {
        const val jUnit = "junit:junit:$jUnitVersion"
        const val androidXJUnit = "androidx.test.ext:junit:$androidXJUnitVersion"
        const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
        const val mockito = "org.mockito:mockito-core:$mockitoVersion"
        const val navigationTesting = "androidx.navigation:navigation-testing:$navigationVersion"
    }
}