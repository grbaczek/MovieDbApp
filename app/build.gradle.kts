import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("kotlin-kapt")
    id("com.android.application")
    kotlin("android")
    //id("com.squareup.sqldelight")
    kotlin(BuildPlugins.serialization) version Versions.Plugins.serialization
}

android {
    compileSdk = ConfigData.compileSdkVersion
    buildToolsVersion = ConfigData.buildToolsVersion

    defaultConfig {
        applicationId = "com.sample.moviedbapp"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        dataBinding = true
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
    implementation(Deps.androidCoroutines)
    implementation(Deps.coroutineCore)
    implementation(Deps.roomRuntime)
    annotationProcessor(Deps.roomCompiler)
    implementation(Deps.roomCoroutines)
    implementation(Deps.roomPaging)
    implementation(Deps.lifeCycleViewModel)
    implementation(Deps.recyclerView)
    kapt(Deps.roomCompiler)

    //implementation(Deps.pagingCommon)
    implementation(Deps.pagingRuntime)

    //Navigation
    implementation(Deps.navigationFragment)
    implementation(Deps.navigationUi)
    implementation(Deps.naviagtionFeatureModule)

    //Retrofit
    implementation(Deps.gson)
    implementation(Deps.retrofit)
    implementation(Deps.retrofitGson)


    testImplementation(Deps.junit)
    testImplementation(Deps.ktorTest)
    //testImplementation(Deps.sqldelightTest)

    androidTestImplementation(Deps.androidxjUnit)
    androidTestImplementation(Deps.espresso)
}