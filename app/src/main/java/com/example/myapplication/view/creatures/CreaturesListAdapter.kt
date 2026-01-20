package com.example.myapplication.view.creatures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.CreaturesListItemBinding
import com.example.myapplication.model.domain.Creature

class CreaturesListAdapter(private val items: MutableList<Creature>) :
    RecyclerView.Adapter<CreaturesListAdapter.ViewHolder>() {

    fun updateList(newItems: List<Creature>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

     class ViewHolder(private val binding: CreaturesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Creature) {
            binding.creature = item
            binding.executePendingBindings()

         // metodo mais simples de se usar pós binding já reconhece todos os ID passados no layout
            Glide.with(binding.ivCreature)
                .load(item.imageUrl)
                .into(binding.ivCreature)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = CreaturesListItemBinding.inflate(layoutInflater, parent, false)

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