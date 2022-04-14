package com.cprt.advancedauction.vehicleSearch.domain.useCase

import com.cprt.advancedauction.core.useCase.UseCase
import kotlinx.coroutines.Dispatchers

internal class FilterListUseCase : UseCase<Pair<String, List<String>>, List<String>>(Dispatchers.Default) {

    override suspend fun execute(parameters: Pair<String, List<String>>): List<String> {
        val filterText = parameters.first
        val manufacturers = parameters.second

        return if (filterText.isEmpty()) {
            manufacturers
        } else {
            manufacturers.filter {
                it.lowercase().contains(filterText.lowercase())
            }
        }
    }
}
