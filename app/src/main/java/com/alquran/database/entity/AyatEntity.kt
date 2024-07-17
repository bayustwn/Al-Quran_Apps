package com.alquran.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "ayat", foreignKeys = [ForeignKey(
    entity = Surah::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("surah_id"),
    onDelete = ForeignKey.CASCADE
)]
)
data class Ayat(
    @PrimaryKey() val id:String="",
    val nomorAyat:Int?=null,
    val teksArab:String?=null,
    val teksLatin:String?=null,
    val teksIndonesia:String?=null,
    @ColumnInfo(name = "surah_id")
    val surahId:Int?=null,
)