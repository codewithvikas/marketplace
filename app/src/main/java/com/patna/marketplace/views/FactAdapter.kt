package com.patna.marketplace.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.patna.marketplace.R
import com.patna.marketplace.databinding.FactListItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ClassCastException

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1
class FactAdapter(val clickListener: FactListItemListener): ListAdapter<DataItem, RecyclerView.ViewHolder>(DataDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is DataItem.HeaderItem -> ITEM_VIEW_TYPE_HEADER
            is DataItem.FactItem -> ITEM_VIEW_TYPE_ITEM
            else -> -1
        }
    }

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list:List<Fact>?){
        adapterScope.launch {
            val items = when(list){
                null -> listOf(DataItem.HeaderItem)
                else -> listOf(DataItem.HeaderItem) + list.map{ DataItem.FactItem(it)}
            }
            withContext(Dispatchers.Main){
                submitList(items)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
             ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
             ITEM_VIEW_TYPE_ITEM -> FactViewHolder.from(parent)
            else -> throw ClassCastException("Unknown view types ${viewType}")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FactViewHolder -> {
                val factItem = getItem(position) as DataItem.FactItem
                holder.bind(factItem.fact,clickListener)
            }
        }

    }


    class FactViewHolder(val binding: FactListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(fact: Fact,clickListener: FactListItemListener) {
            //binding.headingTv.setText(fact.heading)
            binding.fact = fact
            binding.clickListener = clickListener
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
    class HeaderViewHolder(view: View):RecyclerView.ViewHolder(view){
        companion object {
            fun from(parent: ViewGroup):HeaderViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header,parent,false)
                return HeaderViewHolder(view)
            }
        }
    }
}

class DataDiffCallback: DiffUtil.ItemCallback<DataItem>(){
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id.equals(newItem.id)
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
       return oldItem==newItem
    }

}

class FactListItemListener(val clickListener: (factId:Long)->Unit){
    fun onClick(fact:Fact) = clickListener(fact.factId)
}

sealed class DataItem{
    abstract val id:Long
    data class FactItem(val fact: Fact):DataItem(){
        override val id: Long
            get() = fact.factId
    }

    object HeaderItem:DataItem(){
        override val id: Long
            get() = Long.MIN_VALUE
    }
}