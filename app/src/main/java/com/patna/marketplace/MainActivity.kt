package com.patna.marketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.patna.marketplace.databinding.ActivityMainBinding
import com.patna.marketplace.model.Fact
import com.patna.marketplace.model.FactCategory

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        activityMainBinding.fact = Fact(FactCategory.ANIMAL,"Cow is a pet Animal","Cow Gives milk.\n Cow dung is a great source of organic fertilizer")

        activityMainBinding.factsBt.setOnClickListener {
            Toast.makeText(this,"${activityMainBinding.fact}}",Toast.LENGTH_SHORT).show()
        }
        activityMainBinding.blogBt.setOnClickListener {
            Toast.makeText(this,"Blogs coming soon",Toast.LENGTH_SHORT).show()
        }
        activityMainBinding.dealsBt.setOnClickListener {
            Toast.makeText(this,"Offer and deals coming soon",Toast.LENGTH_SHORT).show()
        }
    }

}