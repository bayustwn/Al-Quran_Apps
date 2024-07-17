package com.alquran.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alquran.database.entity.Ayat
import com.alquran.database.entity.Surah
import com.alquran.database.repository.SurahRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SurahViewModel @Inject constructor(private val surahRepository: SurahRepository): ViewModel() {

    fun getAllSurahWithAyat(): Flow<PagingData<Surah>>{
        return Pager(PagingConfig(pageSize = 7, initialLoadSize = 6, enablePlaceholders = false)){
            surahRepository.getAllSurahWithAyat()
        }.flow.cachedIn(viewModelScope)
    }

    fun getAyatBySurahId(surahId: Int): Flow<PagingData<Ayat>>{
        return Pager(PagingConfig(pageSize = 3, initialLoadSize = 3, enablePlaceholders = false)){
            surahRepository.getAyatBySurahId(surahId)
        }.flow.cachedIn(viewModelScope)
    }

    fun getSurahById(surahId: Int): LiveData<Surah> = surahRepository.getSurahById(surahId)

    fun searchSurah(query: String): Flow<PagingData<Surah>> {
        return Pager(PagingConfig(pageSize = 7, initialLoadSize = 6, enablePlaceholders = false)){
            surahRepository.searchSurah(query)
        }.flow.cachedIn(viewModelScope)
    }

}