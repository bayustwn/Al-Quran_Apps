package com.alquran.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.alquran.database.entity.Surah
import com.alquran.databinding.ListSurahBinding
import com.alquran.ui.viewholders.ViewHolder

class SurahAdapter(private val listener: OnClickListener): PagingDataAdapter<Surah, ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListSurahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        data?.let { holder.bind(it, it.id) }
    }

    interface OnClickListener {
        fun onClick(id: Int)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Surah>() {
            override fun areItemsTheSame(oldItem: Surah, newItem: Surah): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Surah, newItem: Surah): Boolean =
                oldItem == newItem
        }
    }
}
