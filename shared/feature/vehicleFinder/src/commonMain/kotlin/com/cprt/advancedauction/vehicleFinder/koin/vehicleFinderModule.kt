package com.cprt.advancedauction.vehicleFinder.koin

import com.cprt.advancedauction.vehicleFinder.domain.useCase.GetActionsUseCase
import com.cprt.advancedauction.vehicleFinder.presentation.ActionsScreenModel
import org.koin.dsl.module

val vehicleFinderModule = module {
    factory {
        ActionsScreenModel(
            getActionsUseCase = get(),
        )
    }
    factory {
        GetActionsUseCase(
            screenProvider = get(),
        )
    }
}
