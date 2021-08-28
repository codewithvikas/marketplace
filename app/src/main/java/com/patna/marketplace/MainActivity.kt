package com.patna.marketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.patna.marketplace.databinding.ActivityMainBinding
import com.patna.marketplace.model.Fact
import com.patna.marketplace.model.FactCategory

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding:ActivityMainBinding
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var appBarConfig: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val root = mainBinding.root
        setContentView(root)

        drawerLayout = mainBinding.drawerLayout

        val navController = this.findNavController(R.id.marketplaceNavHostFragment)

        appBarConfig = AppBarConfiguration(navController.graph,drawerLayout)

        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        NavigationUI.setupWithNavController(mainBinding.navView,navController)

        navController.addOnDestinationChangedListener{
            nc:NavController,nd:NavDestination,args:Bundle? ->
            if (nd.id == nc.graph.startDestination){
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
            else{
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.marketplaceNavHostFragment)
        return NavigationUI.navigateUp(navController,appBarConfig)
    }

}