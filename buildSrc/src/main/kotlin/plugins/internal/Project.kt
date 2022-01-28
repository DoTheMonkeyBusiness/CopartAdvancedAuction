package plugins.internal

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal val Project.libraryExtension: LibraryExtension
    get() = extensions.getByType()

internal val Project.kotlinMultiplatformExtension: KotlinMultiplatformExtension
    get() = extensions.getByType()
