package com.cprt.advancedauction.vehicleSearch.domain.useCase

import com.cprt.advancedauction.core.useCase.UseCase
import kotlinx.coroutines.Dispatchers

internal class GetModelsUseCase : UseCase<String, List<String>>(Dispatchers.IO) {

    override suspend fun execute(parameters: String): List<String> {
        return listOf(
            "Any",
            "E-Class",
            "G-Class",
            "S-Class",
        )
    }
}
