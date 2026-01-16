package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CreaturesListItemBinding
import com.example.myapplication.model.domain.Creature

class CreaturesListAdapter(private val items: List<Creature>) :
    RecyclerView.Adapter<CreaturesListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CreaturesListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Creature){
            binding.creature = item
          //  val tvCreatureNumber = itemView.findViewById<TextView>(R.id.tvCreatureNumber)
         //   val  tvCreatureName = itemView.findViewById<TextView>(R.id.tvCreatureName)


           // tvCreatureNumber.text = item.number.toString()
           // tvCreatureName.text = item.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = CreaturesListItemBinding.inflate(layoutInflater,parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.bindView(item)
    }




    override fun getItemCount() = items.count()


}