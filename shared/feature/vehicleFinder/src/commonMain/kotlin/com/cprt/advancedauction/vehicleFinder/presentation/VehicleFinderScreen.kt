package com.cprt.advancedauction.vehicleFinder.presentation

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cprt.advancedauction.core.screenModel.getScreenModel
import com.cprt.advancedauction.foundation.AACard
import com.cprt.advancedauction.foundation.AADivider
import com.cprt.advancedauction.foundation.spacer.HSpacer
import com.cprt.advancedauction.vehicleFinder.domain.model.ActionModel

internal class VehicleFinderScreen : Screen {

    @Composable
    override fun Content() {
        val scrollState = rememberScrollState()
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .verticalScroll(
                    state = scrollState,
                    enabled = true,
                ),
        ) {
            ExploreActionsBlock(
                navigator = navigator
            )
        }
    }

    @Composable
    private fun ExploreActionsBlock(
        modifier: Modifier = Modifier,
        navigator: Navigator,
    ) {
        val actionsScreenModel = getScreenModel<ActionsScreenModel>()
        val state by actionsScreenModel.stateFlow.collectAsState()
        var actionsList by remember { mutableStateOf<List<ActionModel>>(listOf()) }

        when (val currentSate = state) {
            is ActionsScreenModel.State.Initial -> actionsScreenModel.onInit()
            is ActionsScreenModel.State.Waiting -> Unit
            is ActionsScreenModel.State.ActionsReceived -> {
                actionsList = currentSate.actions
            }
        }

        AnimatedVisibility(
            modifier = modifier,
            visible = actionsList.isNotEmpty(),
            enter = expandVertically(),
            exit = shrinkVertically(),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "Explore Auction Actions",
                    style = MaterialTheme.typography.titleLarge,
                )
                HSpacer(8.dp)
                AADivider()
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(actionsList) {
                        ActionItem(
                            actionModel = it,
                            onClick = { screen ->
                                actionsScreenModel.openScreen(
                                    navigator = navigator,
                                    screen = screen,
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun ActionItem(
        modifier: Modifier = Modifier,
        actionModel: ActionModel,
        onClick: (Screen) -> Unit,
    ) {
        AACard(
            modifier = modifier.size(112.dp),
            backgroundColor = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onClick(actionModel.screenToGo)
                    }
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = actionModel.icon,
                    contentDescription = null,
                )
                HSpacer(4.dp)
                Text(
                    text = actionModel.name,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
    }
}
