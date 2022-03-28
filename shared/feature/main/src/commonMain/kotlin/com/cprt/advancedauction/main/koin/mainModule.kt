package com.cprt.advancedauction.main.koin

import com.cprt.advancedauction.main.presentation.HomeScreenModel
import com.cprt.advancedauction.vehicleFinder.koin.vehicleFinderModule
import org.koin.dsl.module

val mainModule = module {
    factory {
        HomeScreenModel(
            authTokenHolder = get(),
        )
    }

    includes(vehicleFinderModule)
}
