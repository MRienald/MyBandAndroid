package com.mrienaldi.myband

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mrienaldi.myband.databinding.ActivityBottomNavigationBinding
import com.mrienaldi.myband.ui.login.LoginActivity
import com.mrienaldi.myband.util.Prefs

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_navigation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_wishlist, R.id.navigation_catagory
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener {

            if (it.itemId == R.id.navigation_notifications){
                val s = Prefs(this)
                if (s.getIsLogin()){
                    Log.d("TAG", "sudah login")
                    navController.navigate(it.itemId)
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                    Log.d("TAG", "belum login, pindah ke menu login")
                }

            }else{
                navController.navigate(it.itemId)
                Log.d("TAG", "onCreate : yang lain" + it.itemId)
            }

            return@setOnItemSelectedListener true
        }
    }
}