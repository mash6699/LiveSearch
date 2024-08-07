package com.mash.livesearch.domain

import com.mash.livesearch.domain.model.Filter
import com.mash.livesearch.domain.model.SortOption

fun SortOption.toFilter() = Filter(
    label = this.label,
    sort = sortBy,
)