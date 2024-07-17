package com.alquran.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "surah")
data class Surah(
    @PrimaryKey() val id:Int=0,
    val nama:String?=null,
    val namaLatin:String?=null,
    val jumlahAyat:Int?=null,
    val tempatTurun:String?=null,
    val arti:String?=null,
    val deskripsi:String?=null
)

