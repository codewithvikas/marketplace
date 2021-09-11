package com.patna.marketplace.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.patna.marketplace.databinding.FactListItemBinding

class FactAdapter: ListAdapter<Fact, FactAdapter.FactViewHolder>(FactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
       return FactViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        val fact = getItem(position)
        holder.bindData(fact)
    }


    class FactViewHolder(val binding: FactListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindData(fact: Fact) {
            //binding.headingTv.setText(fact.heading)
            binding.fact = fact
            binding.executePendingBindings()

        }
        companion object{
            fun from(parent: ViewGroup):FactViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FactListItemBinding.inflate(layoutInflater,parent,false)
                return FactViewHolder(binding)
            }
        }
    }
}

class FactDiffCallback: DiffUtil.ItemCallback<Fact>(){
    override fun areItemsTheSame(oldItem: Fact, newItem: Fact): Boolean {
        return oldItem.factId.equals(newItem.factId)
    }

    override fun areContentsTheSame(oldItem: Fact, newItem: Fact): Boolean {
       return oldItem==newItem
    }

}