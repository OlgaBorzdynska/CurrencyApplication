package com.example.currencyapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyapplication.data.CurrencyRow
import com.example.currencyapplication.databinding.CurrencyRowBinding

class CurrencyAdapter(private val context: Context) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    inner class CurrencyViewHolder(val binding: CurrencyRowBinding) : RecyclerView.ViewHolder(binding.root)

    var items = ArrayList<CurrencyRow>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(
            CurrencyRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currentRate = items[position]

        holder.binding.apply {
            when(currentRate) {
                is CurrencyRow.DateHeader -> {
                    currencyName.text = currentRate.name + currentRate.date
                    currencyRate.text = ""}
                is CurrencyRow.SingleRate -> {
                    currencyName.text = currentRate.name
                    currencyRate.text = currentRate.rate.toString()}
            }
        }

        holder.itemView.setOnClickListener {
            if (currentRate is CurrencyRow.SingleRate) {
                val detailActivity: Intent = Intent(context, DetailActivity::class.java)
                detailActivity.putExtra("date", currentRate.date)
                detailActivity.putExtra("rate", "${currentRate.name} ${currentRate.rate}")
                startActivity(holder.itemView.context, detailActivity, null)
            }
        }
    }

    override fun getItemCount(): Int = items.size

}

