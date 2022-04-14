package com.cprt.advancedauction.vehicleSearch.domain.model

internal sealed class FilterOption(
    val header: String,
    val textValue: String,
) {
    data class Manufacturer(
        val value: String,
    ) : FilterOption(
        header = "Make",
        textValue = value
    )

    data class Model(
        val value: String,
    ) : FilterOption(
        header = "Model",
        textValue = value
    )

    data class YearRange(
        val range: IntRange
    ) : FilterOption(
        header = "Year Range",
        textValue = "${range.first}-${range.last}",
    )
}
