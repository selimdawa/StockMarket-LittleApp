package com.littleapp.stockmarket.di

import com.littleapp.stockmarket.presentation.data.csv.CSVParser
import com.littleapp.stockmarket.presentation.data.csv.CompanyListingsParser
import com.littleapp.stockmarket.presentation.data.repository.StockRepositoryImpl
import com.littleapp.stockmarket.domain.model.CompanyListing
import com.littleapp.stockmarket.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser,
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl,
    ): StockRepository
}