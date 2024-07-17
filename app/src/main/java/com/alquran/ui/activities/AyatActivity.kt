package com.alquran.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alquran.R
import com.alquran.databinding.ActivityAyatBinding
import com.alquran.ui.adapters.AyatAdapter
import com.alquran.ui.viewmodels.SurahViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AyatActivity : AppCompatActivity() {

    private var _binding: ActivityAyatBinding? = null
    private val binding get() = _binding!!
    private lateinit var ayatAdapter: AyatAdapter

    private val surahViewModel: SurahViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityAyatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.statusBarColor = resources.getColor(R.color.primary, theme)

        val surahId = intent.getIntExtra("surahId",0)

        ayatAdapter = AyatAdapter()
        setupRecycleView()


        lifecycleScope.launch {
            surahViewModel.getAyatBySurahId(surahId).collectLatest{
                ayatAdapter.submitData(it)
            }
        }

        surahViewModel.getSurahById(surahId).observe(this){
            binding.jumlahAyat.text = "${it.jumlahAyat} Ayat"
            binding.tempatTurun.text = it.tempatTurun
            binding.latin.text = it.namaLatin
            binding.arabic.text = it.nama
        }

    }

    private fun setupRecycleView(){
        binding.listAyat.apply {
            this.adapter = ayatAdapter
            this.layoutManager = LinearLayoutManager(
                this@AyatActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            this.setItemViewCacheSize(10)
            this.itemAnimator = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}