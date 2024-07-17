package com.alquran.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.alquran.database.entity.Surah
import com.alquran.databinding.ListSurahBinding
import com.alquran.ui.adapters.SurahAdapter

class ViewHolder(private val binding: ListSurahBinding, private val listener: SurahAdapter.OnClickListener): RecyclerView.ViewHolder(binding.root){

    fun bind(item: Surah,id: Int){
        binding.arabic.text = item.nama
        binding.translate.text = item.arti
        binding.nama.text = item.namaLatin

        binding.root.setOnClickListener {
            listener.onClick(id)
        }

    }
}