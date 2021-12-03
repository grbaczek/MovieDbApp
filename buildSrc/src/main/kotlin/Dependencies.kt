/**
 * To define plugins
 */
object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    val serialization by lazy { "plugin.serialization" }
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
    val ktorClientSerialization by lazy { "io.ktor:ktor-client-serialization:${Versions.ktor}" }

    val sqldelight by lazy { "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}" }
    val sqldelightPaging by lazy {"com.squareup.sqldelight:android-paging3-extensions:${Versions.sqlDelight}"}
    val sqldelightCoroutines by lazy { "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}" }

    //Room
    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }
    val roomCoroutines by lazy { "androidx.room:room-ktx:${Versions.room}" }

    val androidCoroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.androidCoroutines}" }
    val kotlinxSerialization by lazy { "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}" }

    //paging
    val pagingRuntime by lazy { "androidx.paging:paging-runtime:${Versions.paging}" }

    //Test dependencies
    val junit by lazy { "junit:junit:${Versions.jUnit}" }
    val androidxjUnit by lazy { "androidx.test.ext:junit:${Versions.androidjUnit}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.androidEspresso}" }
    val koinTest by lazy { "io.insert-koin:koin-test:${Versions.koin}" }
    val ktorTest by lazy { "io.ktor:ktor-client-mock:${Versions.ktor}" }
    val sqldelightTest by lazy { "com.squareup.sqldelight:sqlite-driver:${Versions.sqlDelight}" }


}
