package com.littleapp.stockmarket.presentation.companylistings

import com.littleapp.stockmarket.Unit.DATA
import com.littleapp.stockmarket.domain.model.CompanyListing

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = DATA.EMPTY,
)
