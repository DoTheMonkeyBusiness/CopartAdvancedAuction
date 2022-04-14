package com.cprt.advancedauction.recources.icons

import androidx.compose.ui.graphics.vector.ImageVector
import com.cprt.advancedauction.recources.icons.aaicons.IcAuction
import com.cprt.advancedauction.recources.icons.aaicons.IcChevronRight
import com.cprt.advancedauction.recources.icons.aaicons.IcCopartLogo
import com.cprt.advancedauction.recources.icons.aaicons.OutlineArrowBack
import kotlin.collections.List as ____KtList

public object AAIcons

private var __AllAssets: ____KtList<ImageVector>? = null

public val AAIcons.AllAssets: ____KtList<ImageVector>
    get() {
        if (__AllAssets != null) {
            return __AllAssets!!
        }
        __AllAssets = listOf(IcAuction, OutlineArrowBack, IcCopartLogo, IcChevronRight)
        return __AllAssets!!
    }
