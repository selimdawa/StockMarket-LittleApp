package com.littleapp.stockmarket.data.repository

import com.littleapp.stockmarket.Unit.DATA
import com.littleapp.stockmarket.data.csv.CSVParser
import com.littleapp.stockmarket.data.local.StockDatabase
import com.littleapp.stockmarket.data.mapper.toCompanyListing
import com.littleapp.stockmarket.data.mapper.toCompanyListingEntity
import com.littleapp.stockmarket.data.remote.StockApi
import com.littleapp.stockmarket.domain.model.CompanyListing
import com.littleapp.stockmarket.domain.repository.StockRepository
import com.littleapp.stockmarket.presentation.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi, db: StockDatabase,
    private val companyListingsParser: CSVParser<CompanyListing>,
) : StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String,
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(
                Resource.Success(
                    data = localListings.map { it.toCompanyListing() }
                )
            )
            val isDbIsEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbIsEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListingEntity() }
                )
                emit(Resource.Success(
                    data = dao.searchCompanyListing(DATA.EMPTY).map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }
}