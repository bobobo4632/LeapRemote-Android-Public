plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    //35:
    //alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "org.mj.leapremote"
    compileSdk = 33
    //35

    defaultConfig {
        applicationId = "org.mj.leapremote"
        minSdk = 24
        targetSdk = 33
        versionCode = 16
        versionName = "1.6"
    }
    useLibrary("org.apache.http.legacy")
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        //35:
        //compose = true
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    //Libraries
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar", "*.jar"))))
    implementation(libs.fastjson)
    implementation(libs.bottom.navigation.bar)
    implementation(libs.lsettingviewlibrary)
    implementation(libs.circleimageview)
    implementation("com.github.li-xiaojun:XPopup:2.9.19")
    annotationProcessor(libs.lombok)
    implementation(libs.lombok)
    implementation(libs.dnsjava)
    implementation("commons-lang:commons-lang:2.6")

    //Android
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.preference:preference-ktx:1.2.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")


    //35:
    /*implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.multidex)
    implementation(libs.androidx.preference.ktx)
    implementation(libs.androidx.runtime)*/

}