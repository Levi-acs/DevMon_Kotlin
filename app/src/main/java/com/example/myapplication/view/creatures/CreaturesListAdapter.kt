package com.example.myapplication.view.creatures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.CreaturesListItemBinding
import com.example.myapplication.model.domain.Creature

class CreaturesListAdapter(
    private val items: List<Creature>, // MUDANÇA AQUI: List ao invés de MutableList
    private val listener: (Creature) -> Unit
) :
    RecyclerView.Adapter<CreaturesListAdapter.ViewHolder>() {

    // Criar uma cópia mutável internamente para a função updateList
    private val mutableItems = items.toMutableList()

    fun updateList(newItems: List<Creature>) {
        mutableItems.clear()
        mutableItems.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: CreaturesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Creature, listener: (Creature) -> Unit) {
            binding.creature = item
            binding.executePendingBindings()

            itemView.setOnClickListener { listener(item) }

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mutableItems[position] // MUDANÇA: usar mutableItems
        holder.bindView(item, listener)
    }

    override fun getItemCount() = mutableItems.count() // MUDANÇA: usar mutableItems
}