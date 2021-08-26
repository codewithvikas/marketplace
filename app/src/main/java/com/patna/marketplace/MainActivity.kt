package com.patna.marketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.patna.marketplace.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val root = activityMainBinding.root
        setContentView(root)
    }
}