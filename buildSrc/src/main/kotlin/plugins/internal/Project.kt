package plugins.internal

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal val Project.libs
    get() = the<LibrariesForLibs>()

internal val Project.libraryExtension: LibraryExtension
    get() = extensions.getByType()

internal val Project.kotlinMultiplatformExtension: KotlinMultiplatformExtension
    get() = extensions.getByType()
