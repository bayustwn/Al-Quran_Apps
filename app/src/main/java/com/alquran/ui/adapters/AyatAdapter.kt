package com.alquran.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alquran.R
import com.alquran.database.entity.Ayat
import com.alquran.database.entity.Surah
import com.alquran.databinding.ListAyatBinding

class AyatAdapter : PagingDataAdapter<Ayat, AyatAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Ayat>() {
            override fun areItemsTheSame(oldItem: Ayat, newItem: Ayat): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Ayat, newItem: Ayat): Boolean =
                oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: ListAyatBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ayat, position: Int, context: Context) {
            binding.ayat.text = item.teksArab
            binding.arti.text = item.teksIndonesia
            binding.lafadz.text = item.teksLatin
            binding.nomorAyat.text = item.nomorAyat.toString()

            if (item.surahId != 1){
                if (item.nomorAyat == 1){
                    binding.basmalah.visibility = View.VISIBLE
                }else{
                    binding.basmalah.visibility = View.GONE
                }
            }else{
                binding.basmalah.visibility = View.GONE
            }

            if (position % 2 == 0) {
                binding.bg.setBackgroundColor(context.getColor(R.color.green_1))
            } else {
                binding.bg.setBackgroundColor(context.getColor(R.color.green_2))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListAyatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        data?.let {
            holder.bind(it, position, holder.itemView.context)
        }
    }
}

