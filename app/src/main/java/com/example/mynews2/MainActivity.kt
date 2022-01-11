package com.example.mynews2

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mynews2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val adapter=ViewPagerAdapter(supportFragmentManager,lifecycle)
        setContentView(viewBinding.root)



        viewBinding.viewPager.adapter=adapter
        TabLayoutMediator(viewBinding.tabLayout,viewBinding.viewPager){tab,position->
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

        toggle= ActionBarDrawerToggle(this,viewBinding.drawerLayout,R.string.open,R.string.close)

        viewBinding.drawerLayout.addDrawerListener(toggle)
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