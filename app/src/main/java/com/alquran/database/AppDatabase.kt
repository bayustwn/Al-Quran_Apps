package com.alquran.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alquran.database.dao.SurahDao
import com.alquran.database.entity.Ayat
import com.alquran.database.entity.Surah

@Database(entities = [Surah::class,Ayat::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun surahDao(): SurahDao


}
