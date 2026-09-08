package com.littleapp.stockmarket.di

import android.app.Application
import androidx.room.Room
import com.littleapp.stockmarket.data.StockDao
import com.littleapp.stockmarket.data.StockDatabase
import com.littleapp.stockmarket.model.CompanyListing
import com.littleapp.stockmarket.network.CSVParser
import com.littleapp.stockmarket.network.CompanyListingsParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockDatabase(app: Application): StockDatabase {
        return Room.databaseBuilder(app, StockDatabase::class.java, "stockdb.db").build()
    }

    @Provides
    @Singleton
    fun provideStockDao(db: StockDatabase): StockDao = db.dao

    @Provides
    @Singleton
    fun provideCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListing> = companyListingsParser
}