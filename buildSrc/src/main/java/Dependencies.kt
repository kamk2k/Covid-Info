private const val kotlinVersion = "1.3.70"
private const val androidGradleVersion = "4.1.0-alpha04"

private const val ktxVersion = "1.2.0"
private const val appcompatVersion = "1.1.0"
private const val constraintLayoutVersion = "1.1.3"

private const val jUnitVersion = "4.12"
private const val androidXJUnitVersion = "1.1.1"
private const val espressoVersion = "3.2.0"

class Dependencies {
    object BuildPlugins {
        const val androidGradle = "com.android.tools.build:gradle:$androidGradleVersion"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    }

    object Android {
        const val buildToolsVersion = "29.0.1"
        const val minSdkVersion = 19
        const val targetSdkVersion = 29
        const val compileSdkVersion = 29
        const val versionCode = 1
        const val versionName = "0.1"
    }

    object Libs {
        const val ktx = "androidx.core:core-ktx:$ktxVersion"
        const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
    }

    object TestLibs {
        const val jUnit = "junit:junit:$jUnitVersion"
        const val androidXJUnit = "androidx.test.ext:junit:$androidXJUnitVersion"
        const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
    }
}