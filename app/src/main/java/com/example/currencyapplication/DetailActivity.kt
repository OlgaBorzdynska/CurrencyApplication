package com.example.currencyapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.currencyapplication.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        if(intent.hasExtra("date")) binding.textViewDate.text = intent.getCharSequenceExtra("date")
        if(intent.hasExtra("rate")) binding.textViewRate.text = intent.getCharSequenceExtra("rate")

    }
}