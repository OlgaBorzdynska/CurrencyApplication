package com.example.currencyapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.currencyapplication.common.Resource
import com.example.currencyapplication.data.DateCurrencyDto
import com.example.currencyapplication.data.toDateRatesList
import com.example.currencyapplication.databinding.ActivityMainBinding
import com.example.currencyapplication.viewmodel.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CurrencyViewModel by viewModels()
    private lateinit var currencyAdapter: CurrencyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currencyAdapter = CurrencyAdapter(applicationContext)

        binding.recyclerView.apply {
            adapter = currencyAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration  = DividerItemDecoration(this@MainActivity, StaggeredGridLayoutManager.VERTICAL)
            binding.recyclerView.addItemDecoration(decoration)
        }

        binding.nestedScrollView.setOnScrollChangeListener {
                nestedScrollView: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->

            if (scrollY == nestedScrollView.getChildAt(0).measuredHeight - nestedScrollView.measuredHeight) {
                binding.progressBar.visibility = View.VISIBLE;
                viewModel.getCurrencyPreviousDay()
            }
        }

        viewModel.currencyResponse.observe(this) {
            when (it) {
                is Resource.Success<DateCurrencyDto> -> {
                    currencyAdapter.items = viewModel.getItemsLoadedForAdapter()
                    currencyAdapter.notifyDataSetChanged()
                }
                is Resource.Error<DateCurrencyDto> -> {
                    Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_LONG).show()
                }
                else -> {
                    Toast.makeText(this, "Unknown error", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}