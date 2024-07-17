package com.alquran.database.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Query
import com.alquran.database.AppDatabase
import com.alquran.database.entity.Ayat
import com.alquran.database.entity.Surah
import javax.inject.Inject

class SurahRepository @Inject constructor(private val appDatabase: AppDatabase) {

    fun getAllSurahWithAyat(): PagingSource<Int,Surah> = appDatabase.surahDao().getAllSurahWithAyat()
    fun getAyatBySurahId(surahId: Int): PagingSource<Int,Ayat> = appDatabase.surahDao().getAyatBySurahId(surahId)
    fun getSurahById(surahId: Int): LiveData<Surah> = appDatabase.surahDao().getSurahById(surahId)
    fun searchSurah(query: String): PagingSource<Int,Surah> = appDatabase.surahDao().searchSurah(query)
}