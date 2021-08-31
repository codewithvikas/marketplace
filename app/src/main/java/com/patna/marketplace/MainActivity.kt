package com.patna.marketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.patna.marketplace.databinding.ActivityMainBinding
import com.patna.marketplace.model.Fact
import com.patna.marketplace.model.FactCategory

class MainActivity : AppCompatActivity() {


    private lateinit var mainBinding:ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfig: AppBarConfiguration
    val timerExample = TimerExample()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = this.findNavController(R.id.marketplaceNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController)


        timerExample.startTimer()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.marketplaceNavHostFragment)
        return navController.navigateUp()
    }

    override fun onStart() {
        super.onStart()
        timerExample.stopTimer()
    }
}