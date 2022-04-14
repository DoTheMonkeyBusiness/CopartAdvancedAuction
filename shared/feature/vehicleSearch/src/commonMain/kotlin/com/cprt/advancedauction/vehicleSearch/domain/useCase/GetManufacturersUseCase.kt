package com.cprt.advancedauction.vehicleSearch.domain.useCase

import com.cprt.advancedauction.core.useCase.UseCase
import kotlinx.coroutines.Dispatchers

internal class GetManufacturersUseCase : UseCase<Unit, List<String>>(Dispatchers.IO) {

    override suspend fun execute(parameters: Unit): List<String> {
        return listOf(
            "Any",
            "Alfa Romeo",
            "BMW",
            "Chrysler",
            "Dodge",
            "Mercedes",
            "Rolls-Royce",
            "Volksvagen",
        )
    }
}
