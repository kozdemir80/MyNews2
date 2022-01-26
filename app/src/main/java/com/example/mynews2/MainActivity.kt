package com.example.mynews2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mynews2.NewsApi.Repository
import com.example.mynews2.View.ViewPagerAdapter
import com.example.mynews2.ViewModel.MostPopularViewModel
import com.example.mynews2.ViewModel.ViewModelFactory
import com.example.mynews2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val adapter= ViewPagerAdapter(supportFragmentManager,lifecycle)
        setContentView(binding.root)





        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navView) as NavHostFragment?
        val navController: NavController? = navHostFragment?.navController

        if (navController != null) {
            binding.navView.setupWithNavController(navController)
        }


        binding.viewPager.adapter=adapter
        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
            when(position){
                0->{
                    tab.text="TOP STORIES"
                }
                1->{
                    tab.text="MOST POPULAR"
                }
                2->{
                    tab.text="BUSINESS"
                }
            }

        }.attach()

        toggle= ActionBarDrawerToggle(this,binding.drawerLayout,R.string.open,R.string.close)

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


   }







    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.new_menu, menu)
        menuInflater.inflate(R.menu.search_view,menu)


        return super.onCreateOptionsMenu(menu)
    }

}