package com.cprt.advancedauction.theme.icons

import androidx.compose.ui.graphics.vector.ImageVector
import com.cprt.advancedauction.theme.icons.aaicons.IcCopartLogo
import com.cprt.advancedauction.theme.icons.aaicons.OutlineArrowBack
import kotlin.collections.List as ____KtList

public object AAIcons

private var __AllAssets: ____KtList<ImageVector>? = null

public val AAIcons.AllAssets: ____KtList<ImageVector>
    get() {
        if (__AllAssets != null) {
            return __AllAssets!!
        }
        __AllAssets = listOf(OutlineArrowBack, IcCopartLogo)
        return __AllAssets!!
    }
