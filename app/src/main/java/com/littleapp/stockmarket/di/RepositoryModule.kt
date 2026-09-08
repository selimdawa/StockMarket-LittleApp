package com.littleapp.stockmarket.di

import com.littleapp.stockmarket.network.CSVParser
import com.littleapp.stockmarket.network.CompanyListingsParser
import com.littleapp.stockmarket.model.CompanyListing
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
    abstract fun bindCSVParser(parser: CompanyListingsParser): CSVParser<CompanyListing>
}