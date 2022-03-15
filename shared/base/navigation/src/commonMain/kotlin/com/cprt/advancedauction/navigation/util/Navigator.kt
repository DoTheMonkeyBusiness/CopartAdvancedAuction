package com.cprt.advancedauction.navigation.util

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

fun Navigator.replaceIfPossible(item: Screen) {
    if (!lastItem::class.isInstance(item)) {
        replace(item)
    }
}
