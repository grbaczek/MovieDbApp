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

    //Room
    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }
    val roomCoroutines by lazy { "androidx.room:room-ktx:${Versions.room}" }
    val roomPaging by lazy { "androidx.room:room-paging:${Versions.roomPaging}" }

    //Retrofit
    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }
    val retrofit by lazy {"com.squareup.retrofit2:retrofit:${Versions.retrofit}"}
    val retrofitGson by lazy {"com.squareup.retrofit2:converter-gson:${Versions.retrofit}"}

    val androidCoroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.androidCoroutines}" }

    //paging
    val pagingRuntime by lazy { "androidx.paging:paging-runtime:${Versions.paging}" }

    //navigation
    val navigationFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.nav}" }
    val navigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.nav}" }
    val naviagtionFeatureModule by lazy { "androidx.navigation:navigation-dynamic-features-fragment:${Versions.nav}" }

    //Test dependencies
    val junit by lazy { "junit:junit:${Versions.jUnit}" }
    val androidxjUnit by lazy { "androidx.test.ext:junit:${Versions.androidjUnit}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.androidEspresso}" }
    val koinTest by lazy { "io.insert-koin:koin-test:${Versions.koin}" }
    val ktorTest by lazy { "io.ktor:ktor-client-mock:${Versions.ktor}" }
    val sqldelightTest by lazy { "com.squareup.sqldelight:sqlite-driver:${Versions.sqlDelight}" }


}
