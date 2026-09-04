import com.google.gms.googleservices.GoogleServicesPlugin.MissingGoogleServicesStrategy

plugins {
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.google.devtools.ksp)
  alias(libs.plugins.roborazzi)
}

android {
  namespace = "com.zakootaapps.ilmjafar"
  compileSdk { version = release(36) { minorApiLevel = 1 } }

  defaultConfig {
    applicationId = "com.zakootaapps.ilmjafar"
    minSdk = 24
    targetSdk = 36
    versionCode = 1
    versionName = "1.0.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  signingConfigs {
    getByName("debug") {
      storeFile = file("../debug.keystore")
      storePassword = "android"
      keyAlias = "androiddebugkey"
      keyPassword = "android"
    }
    create("release") {
      val keystorePath = System.getenv("KEYSTORE_PATH")
      val storePass = System.getenv("STORE_PASSWORD")
      val keyAliasValue = System.getenv("KEY_ALIAS")
      val keyPass = System.getenv("KEY_PASSWORD")
      val isReleaseBuild = gradle.startParameter.taskNames.any { it.contains("Release") }

      if (keystorePath == null || storePass == null || keyAliasValue == null || keyPass == null) {
          if (isReleaseBuild) {
              throw GradleException("Release build failed: Missing KEYSTORE_PATH, STORE_PASSWORD, KEY_ALIAS, or KEY_PASSWORD environment variables.")
          }
      } else {
          storeFile = file(keystorePath)
          storePassword = storePass
          keyAlias = keyAliasValue
          keyPassword = keyPass
      }
    }
      }

  buildTypes {
    release {
      isCrunchPngs = false
      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      signingConfig = signingConfigs.getByName("release")
    }
      }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  packagingOptions {
    jniLibs {
      useLegacyPackaging = false
    }
  }

  buildFeatures {
    compose = true
    buildConfig = true
  }
  testOptions { unitTests { isIncludeAndroidResources = true } }
}




ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}
