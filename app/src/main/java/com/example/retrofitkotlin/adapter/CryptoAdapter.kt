package com.example.retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.databinding.CryptoRowBinding
import com.example.retrofitkotlin.modul.Crypto
import kotlinx.android.synthetic.main.crypto_row.view.*

class CryptoAdapter(var array: ArrayList<Crypto>,private val listener:Listener):RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {
    private val colors:Array<String> = arrayOf("#FF0000","#00FF00","#0000FF","#FFFF00","#800000","#808000","#800080")

    interface Listener{
        fun onItemClick(crypto: Crypto)
    }

    class CryptoHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(crypto: Crypto,colors:Array<String>,position: Int,listener: Listener){
            itemView.setOnClickListener{
                listener.onItemClick(crypto)
            }


            itemView.setBackgroundColor(Color.parseColor(colors.get(position % 7)))
            itemView.currentState.text=crypto.currency
            itemView.priceState.text=crypto.price
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {

        val view=LayoutInflater.from(parent.context).inflate(R.layout.crypto_row,parent,false)
        return CryptoHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
            holder.bind(array.get(position),colors,position,listener)


    }

    override fun getItemCount(): Int {

        return array.size

    }
}