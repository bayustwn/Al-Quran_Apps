package com.alquran.database

import android.content.Context
import androidx.core.os.BuildCompat
import androidx.room.Room
import com.alquran.BuildConfig
import com.alquran.database.dao.SurahDao
import com.alquran.database.entity.Ayat
import com.alquran.database.entity.Surah
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    private var INSTANCE : AppDatabase? = null

    @Provides
    @Singleton
    fun getInstance(@ApplicationContext context: Context):AppDatabase {
        return INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                BuildConfig.DATABASE
            ).createFromAsset(BuildConfig.DATABASE_NAME)
                .build()
            INSTANCE = instance
            instance
        }
    }

    @Provides
    fun providesDao(db: AppDatabase) : SurahDao{
        return db.surahDao()
    }


}