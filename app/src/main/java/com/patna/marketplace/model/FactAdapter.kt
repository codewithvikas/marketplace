package com.patna.marketplace.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.patna.marketplace.R

class FactAdapter: RecyclerView.Adapter<FactAdapter.FactViewHolder>() {


    var data = listOf<Fact>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
       return FactViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        val fact = data.get(position)
        holder.bindData(fact)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class FactViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

        private val headingView:TextView
        private val bodyView:TextView
        init {
            headingView = itemView.findViewById(R.id.heading_tv)
            bodyView = itemView.findViewById(R.id.body_tv)
        }
        fun bindData(fact: Fact){
            headingView.setText(fact.heading)
            bodyView.setText(fact.body)
        }
        companion object{
            fun from(parent: ViewGroup):FactViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.fact_list_item,parent,false)
                return FactViewHolder(view)
            }
        }
    }
}