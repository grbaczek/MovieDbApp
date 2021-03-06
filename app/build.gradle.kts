plugins {
    id("com.android.application")
    kotlin("android")
    id("com.squareup.sqldelight")
    kotlin(BuildPlugins.serialization) version Versions.Plugins.serialization
}

android {
    compileSdk = ConfigData.compileSdkVersion
    buildToolsVersion = ConfigData.buildToolsVersion

    defaultConfig {
        applicationId = "com.sample.dsl"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(Deps.kotlin)
    implementation(Deps.appCompat)
    implementation(Deps.materialDesign)
    implementation(Deps.timber)
    implementation(Deps.constraintLayout)
    implementation(Deps.koin)
    implementation(Deps.koinJava)
    implementation(Deps.koinNavigation)
    implementation(Deps.koinTest)
    implementation(Deps.ktorCore)
    implementation(Deps.ktorAndroid)
    implementation(Deps.androidCoroutines)
    implementation(Deps.sqldelight)
    implementation(Deps.sqldelightCoroutines)
    implementation(Deps.kotlinxSerialization)
    implementation(Deps.ktorClientSerialization)


    testImplementation(Deps.junit)
    testImplementation(Deps.ktorTest)
    testImplementation(Deps.sqldelightTest)

    androidTestImplementation(Deps.androidxjUnit)
    androidTestImplementation(Deps.espresso)
}