package com.littleapp.stockmarket.data.repository

import com.littleapp.stockmarket.data.csv.CSVParser
import com.littleapp.stockmarket.data.local.StockDao
import com.littleapp.stockmarket.data.mapper.toCompanyListing
import com.littleapp.stockmarket.data.mapper.toCompanyListingEntity
import com.littleapp.stockmarket.data.remote.StockApi
import com.littleapp.stockmarket.domain.model.CompanyListing
import com.littleapp.stockmarket.domain.repository.StockRepository
import com.littleapp.stockmarket.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val dao: StockDao,
    private val parser: CSVParser<CompanyListing>,
) : StockRepository {
    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String,
    ): Flow<Resource<List<CompanyListing>>> = flow {
        emit(Resource.Loading(true))
        val local = dao.searchCompanyListing(query)
        emit(Resource.Success(data = local.map { it.toCompanyListing() }))

        if (local.isNotEmpty() && !fetchFromRemote && query.isBlank()) {
            emit(Resource.Loading(false))
            return@flow
        }

        try {
            val remote = parser.parse(api.getListings().byteStream())
            dao.clearCompanyListings()
            dao.insertCompanyListings(remote.map { it.toCompanyListingEntity() })
            emit(Resource.Success(data = dao.searchCompanyListing("").map { it.toCompanyListing() }))
        } catch (e: Exception) {
            emit(Resource.Error("Error loading data"))
        } finally {
            emit(Resource.Loading(false))
        }
    }
}