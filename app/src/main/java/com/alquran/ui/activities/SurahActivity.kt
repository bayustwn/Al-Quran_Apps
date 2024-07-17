package com.alquran.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alquran.R
import com.alquran.databinding.ActivitySurahBinding
import com.alquran.ui.adapters.SurahAdapter
import com.alquran.ui.viewmodels.SurahViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SurahActivity : AppCompatActivity(), SurahAdapter.OnClickListener {

    private var _binding: ActivitySurahBinding? = null
    private val binding get() = _binding!!
    private lateinit var surahAdapter: SurahAdapter

    private val surahViewModel: SurahViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        _binding = ActivitySurahBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        surahAdapter = SurahAdapter(this)
        setupRecycleView()


        lifecycleScope.launch(Dispatchers.IO){
            surahViewModel.getAllSurahWithAyat().collect{
                surahAdapter.submitData(it)
            }
        }

        binding.search.apply {
            this.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    binding.clear.visibility = View.VISIBLE
                    lifecycleScope.launch(Dispatchers.IO) {
                        surahViewModel.searchSurah(this@apply.text.toString()).collect{
                            surahAdapter.submitData(it)
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    if (this@apply.text.isNullOrEmpty()){
                        binding.clear.visibility = View.GONE
                    }
                }

            })

        }

        binding.clear.setOnClickListener{
            binding.search.text.clear()
        }

    }

    private fun setupRecycleView(){
       binding.listSurah.apply {
          this.adapter = surahAdapter
           this.layoutManager = LinearLayoutManager(
               this@SurahActivity,
               LinearLayoutManager.VERTICAL,
               false
           )
           this.itemAnimator = null
       }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(id: Int) {
        val intent = Intent(this@SurahActivity,AyatActivity::class.java)
            .putExtra("surahId",id)
        startActivity(intent)
    }


}