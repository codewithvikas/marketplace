package com.patna.marketplace.views

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.patna.marketplace.model.Fact

@BindingAdapter("item_heading")
fun TextView.setFactHeading(item: Fact?){
    item?.let {
        text = item.heading
    }
}