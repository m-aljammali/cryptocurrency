// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {
    extra.apply {
        set("room_version", "2.6.1")
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.android.library") version "8.5.2" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0" apply false
}