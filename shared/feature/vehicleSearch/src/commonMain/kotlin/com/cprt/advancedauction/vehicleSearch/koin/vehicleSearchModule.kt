package com.cprt.advancedauction.vehicleSearch.koin

import com.cprt.advancedauction.vehicleSearch.domain.useCase.FilterListUseCase
import com.cprt.advancedauction.vehicleSearch.domain.useCase.GetManufacturersUseCase
import com.cprt.advancedauction.vehicleSearch.domain.useCase.GetModelsUseCase
import com.cprt.advancedauction.vehicleSearch.presentation.VehicleSearchScreenModel
import com.cprt.advancedauction.vehicleSearch.presentation.subscreen.chooseYear.ChooseYearScreenModel
import com.cprt.advancedauction.vehicleSearch.presentation.subscreen.filterable.chooseManufacturer.ChooseManufacturerScreenModel
import com.cprt.advancedauction.vehicleSearch.presentation.subscreen.filterable.chooseModel.ChooseModelScreenModel
import org.koin.dsl.module

val vehicleSearchModule = module {
    factory {
        VehicleSearchScreenModel(
            dateTime = get()
        )
    }
    factory {
        ChooseManufacturerScreenModel(
            filterListUseCase = get(),
            getManufacturersUseCase = get(),
        )
    }
    factory {
        ChooseModelScreenModel(
            filterListUseCase = get(),
            getModelsUseCase = get(),
        )
    }
    factory {
        ChooseYearScreenModel(
            dateTime = get(),
            internalNotificationManager = get(),
        )
    }
    factory {
        FilterListUseCase()
    }
    factory {
        GetManufacturersUseCase()
    }
    factory {
        GetModelsUseCase()
    }
}
