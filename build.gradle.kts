buildscript {
    dependencies {
        classpath(libs.google.services)
    }
}
<<<<<<< HEAD
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false

}
=======

plugins {
    id("com.android.application") version "8.1.2" apply false
}
>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
