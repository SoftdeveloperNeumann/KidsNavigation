package com.example.kidsnavigation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.kidsnavigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        navController = (supportFragmentManager.findFragmentById(R.id.fragment_container)as NavHostFragment).navController

        setContentView(binding.root)

        with(binding.kidsToolbar){
            title="Kids-App"
            setSupportActionBar(this)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toggle = ActionBarDrawerToggle(this,binding.main,R.string.open,R.string.close)
        binding.main.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_calendar ->{navController.navigate(R.id.action_global_kalenderFragment)}
                R.id.nav_contacts ->{navController.navigate(R.id.action_global_kontakteFragment)}
                R.id.nav_home ->{navController.navigate(R.id.action_global_mainFragment)}
                R.id.nav_learn->{navController.navigate(R.id.action_global_learnFragment)}
                R.id.nav_medical ->{navController.navigate(R.id.action_global_medicalFragment)}
                R.id.nav_parents ->{navController.navigate(R.id.action_global_parentFragment)}
                R.id.nav_social ->{navController.navigate(R.id.action_global_socialFragment)}
                R.id.nav_settings ->{navController.navigate(R.id.action_global_settingsFragment)}

            }
            binding.main.closeDrawer(GravityCompat.START)
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

}