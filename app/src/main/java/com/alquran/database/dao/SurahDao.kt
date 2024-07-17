package com.alquran.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.alquran.database.entity.Ayat
import com.alquran.database.entity.Surah

@Dao
interface SurahDao {
    @Transaction
    @Query("SELECT * FROM surah")
    fun getAllSurahWithAyat(): PagingSource<Int,Surah>

    @Query("SELECT * FROM ayat WHERE surah_id = :surahId")
    fun getAyatBySurahId(surahId: Int): PagingSource<Int,Ayat>

    @Query("SELECT * FROM surah WHERE id = :surahId")
    fun getSurahById(surahId:Int): LiveData<Surah>

    @Query("SELECT * FROM surah WHERE namaLatin LIKE '%' || :query || '%' ")
    fun searchSurah(query: String): PagingSource<Int,Surah>
}