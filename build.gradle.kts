// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    // ksp
    alias(libs.plugins.kotlin.devtools.ksp) apply false

    // dagger hilt
    alias(libs.plugins.android.dagger.hilt) apply  false

    // room
    alias(libs.plugins.androidx.room) apply false
}
