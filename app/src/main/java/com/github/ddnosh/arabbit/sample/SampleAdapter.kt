package com.github.ddnosh.arabbit.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SampleAdapter(private val sampleList: Array<String>, private val onClick: (Int) -> Unit) :
        RecyclerView.Adapter<SampleAdapter.SampleViewHolder>() {

    class SampleViewHolder(itemView: View, private val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val flowerTextView: TextView = itemView.findViewById(R.id.sample_text)
        private var currentPosition: Int = 0

        init {
            itemView.setOnClickListener {
                onClick(currentPosition)
            }
        }
        fun bind(position: Int, sampleName: String) {
            currentPosition = position
            flowerTextView.text = sampleName
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.sample_item, parent, false)

        return SampleViewHolder(view, onClick)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return sampleList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(position, sampleList[position])
    }
}