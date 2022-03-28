package com.cprt.advancedauction.vehicleFinder.domain.useCase

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Search
import com.cprt.advancedauction.core.tools.ScreenProvider
import com.cprt.advancedauction.core.useCase.UseCase
import com.cprt.advancedauction.theme.icons.AAIcons
import com.cprt.advancedauction.theme.icons.aaicons.IcAuction
import com.cprt.advancedauction.vehicleFinder.domain.model.ActionModel
import kotlinx.coroutines.Dispatchers

internal class GetActionsUseCase(
    private val screenProvider: ScreenProvider,
) : UseCase<Unit, List<ActionModel>>(Dispatchers.Main) {

    override suspend fun execute(parameters: Unit): List<ActionModel> {
        return listOf(
            ActionModel(
                name = "Finder",
                icon = Icons.Outlined.Search,
                screenToGo = screenProvider.onBoardingScreen,
            ),
            ActionModel(
                name = "Auctions",
                icon = AAIcons.IcAuction,
                screenToGo = screenProvider.onBoardingScreen,
            ),
            ActionModel(
                name = "Locations",
                icon = Icons.Outlined.Place,
                screenToGo = screenProvider.onBoardingScreen,
            ),
        )
    }
}
