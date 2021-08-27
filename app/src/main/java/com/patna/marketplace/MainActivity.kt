package com.patna.marketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.patna.marketplace.databinding.ActivityMainBinding
import com.patna.marketplace.model.Fact
import com.patna.marketplace.model.FactCategory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}