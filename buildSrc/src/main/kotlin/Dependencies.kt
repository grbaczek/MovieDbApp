/**
 * To define plugins
 */
object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
}

/**
 * To define dependencies
 */
object Deps {
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }
    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val koin by lazy { "io.insert-koin:koin-android:${Versions.koin}" }
    val koinJava by lazy { "io.insert-koin:koin-android-compat:${Versions.koin}" }
    val koinNavigation by lazy { "io.insert-koin:koin-androidx-navigation:${Versions.koin}" }
    val ktorCore by lazy { "io.ktor:ktor-client-core:${Versions.ktor}" }
    val ktorAndroid by lazy { "io.ktor:ktor-client-android:${Versions.ktor}" }
    val sqldelight by lazy { "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}" }
    val sqldelightCoroutines by lazy {"com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}"}
    val androidCoroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.androidCoroutines}"}

    //Test dependencies
    val junit by lazy { "junit:junit:${Versions.jUnit}" }
    val koinTest by lazy { "io.insert-koin:koin-test:${Versions.koin}" }
    val ktorTest by lazy { "io.ktor:ktor-client-mock:${Versions.koin}" }
    val sqldelightTest by lazy {"com.squareup.sqldelight:sqlite-driver:${Versions.sqlDelight}"}


}
