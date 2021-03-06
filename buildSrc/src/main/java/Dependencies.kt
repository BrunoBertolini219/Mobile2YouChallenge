package br.com.brunoccbertolini.buildSrc

object Dependencies {

    object Modules {
        private const val data = ":data"
        private const val domain = ":domain"
        private const val presentation = ":presentation"
        private const val app = ":app"

        val appModule = listOf(
            data,
            domain,
            presentation
        )
        val presentationModule = listOf(
            domain
        )
        val dataModules = listOf(
            domain
        )
    }


    object CoreLibs {
        const val kotlinCore = "androidx.core:core-ktx:${Versions.coreAndroidKtx}"
        private const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        private const val material =
            "com.google.android.material:material:${Versions.googleMaterial}"
        private const val hilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
        private const val hiltKptCompiler =
            "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
        private const val hiltViewModel =
            "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
        private const val hiltKptAndroidX = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"
        private const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
        private const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
        private const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
        private const val archComponent =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.architectualComp}"
        private const val coroutinesRunlKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.liveData}"
        private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        private const val retrofitGson =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofitGson}"
        private const val okHttpInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3Interceptior}"
        private const val constLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        private const val liveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveData}"
        private const val recyclerView =
            "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        private const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
        private const val legacySupport =
            "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
        private const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        private const val navigationUi =
            "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        private const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        private const val glideKpt = "com.github.bumptech.glide:compiler:${Versions.glide}"


        val appDep = listOf(
            kotlinCore,
            appCompat,
            material,
            constLayout,
            liveData,
            recyclerView,
            cardView,
            legacySupport,
            navigationFragment,
            navigationUi,
            glide,
            hilt,
            hiltKptAndroidX,
            coroutinesRunlKtx,
            archComponent
        )


        val presentationDep = listOf(
            kotlinCore,
            material,
            appCompat,
            hilt,
            hiltKptAndroidX,
            coroutinesCore,
            hiltViewModel,
            archComponent,
            coroutinesRunlKtx

        )


        val androidX = listOf(
            kotlinCore,
            appCompat,
            hiltViewModel,
            hiltKptAndroidX,
            paging,
            coroutinesCore,
            archComponent,
            coroutinesRunlKtx
        )

        val hiltKpt = listOf(
            hiltKptCompiler,
            coroutinesAndroid
        )

        val dataDep = listOf(
            retrofit,
            retrofitGson,
            okHttpInterceptor,
            hilt,
            material
        )

    }

    object Domain {
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.coreAndroidKtx}"
    }


    object AndroidTests {
        private const val testCoreAndroidX = "androidx.test:core:${VersionsTest.testCore}"

        //Test
        private const val junit = "junit:junit:${VersionsTest.junitTest}"
        private const val hamcrestTest = "org.hamcrest:hamcrest-all:${VersionsTest.hamcrestTest}"
        private const val archCoreTest =
            "androidx.arch.core:core-testing:${VersionsTest.archCoreTest}"
        private const val robolectricTest =
            "org.robolectric:robolectric:${VersionsTest.robolectricTest}"
        private const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${VersionsTest.coroutinesTest}"
        private const val truthTest = "com.google.truth:truth:${VersionsTest.truthInstrumentedTest}"
        private const val mockitoTest = "org.mockito:mockito-core:${VersionsTest.mockitoCoreTest}"
        private const val mockkTest = "io.mockk:mockk:${VersionsTest.mockk}"


        //Instrumented
        private const val espressoContrib =
            "androidx.test.espresso:espresso-contrib:${VersionsTest.espressoContribInstrumented}"
        private const val extJunit = "androidx.test.ext:junit:${VersionsTest.extJunitTest}"
        private const val espressoCore =
            "androidx.test.espresso:espresso-core:${VersionsTest.espressoCoreInstrumented}"
        private const val hiltTest =
            "com.google.dagger:hilt-android-testing:${VersionsTest.hiltInstrumented}"

        const val hiltAndroidTestKapt =
            "com.google.dagger:hilt-android-compiler:${VersionsTest.hiltCompilerKapt}"
        const val debugFragmentTest =
            "androidx.fragment:fragment-testing:${VersionsTest.testFragmentDebug}"


        val domainTest = listOf(
            junit,
            mockkTest,
            coroutinesTest

        )

        val test = listOf(
            junit,
            hamcrestTest,
            archCoreTest,
            robolectricTest,
            coroutinesTest,
            truthTest,
            mockitoTest,
            mockkTest
        )

        val instrumentedTest = listOf(
            extJunit,
            espressoCore,
            junit,
            archCoreTest,
            truthTest,
            mockitoTest,
            coroutinesTest,
            espressoContrib,
            hiltTest,
            mockkTest
        )
    }
}

