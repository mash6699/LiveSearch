package com.mash.livesearch.domain.model

data class APIProductResponse(
    val status: StatusResponse,
    val pageType: String,
    val plpResults: PlpResult
)

data class StatusResponse(
    val status: String,
    val statusCode: String
)

data class PlpResult(
    val label: String?,
    val plpState: PlpState?,
    val sortOptions: List<SortOption>,
    val refinementGroups: List<RefinementGroup>,
    val records: List<ProductRecord>?
)

data class PlpState( // pagination
    val categoryId: String,
    val currentSortOption: String,
    val currentFilters: String,
    val firstRecNum: Int,
    val lastRecNum: Int,
    val recsPerPage: Int,
    val totalNumRecs: Int,
    val originalSearchTerm: String?,
    val plpSellerName: String?,
)

data class SortOption(
    val sortBy: String,
    val label: String
)

data class RefinementGroup(
    val name: String,
    val refinement: List<Refinement>,
    val multiSelect: Boolean,
    val dimensionName: String
)

data class Refinement(
    val count: Int,
    val label: String,
    val refinementId: String,
    val selected: Boolean,
    val type: String,
    val searchName: String?,
    val high: String?,
    val low: String?,
)

data class ProductRecord(
    val productDisplayName: String,
    val productRatingCount: Int,
    val listPrice: Double,
    val promoPrice: Double,
    val variantsColor: List<VariantColor>?,
    val lgImage: String?,
)

data class VariantColor(
    var colorName: String?,
    var colorHex: String?
)