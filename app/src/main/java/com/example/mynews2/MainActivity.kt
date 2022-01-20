package com.example.mynews2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews2.Model.NewsArticle
import com.example.mynews2.View.NewsAdapter
import com.example.mynews2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

   var recyclerAdapter: RecyclerView = findViewById(R.id.recyclerAdapter)
    val newsList= ArrayList<NewsArticle>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val adapter=ViewPagerAdapter(supportFragmentManager,lifecycle)
        setContentView(viewBinding.root)



        try {
        recyclerAdapter.layoutManager=LinearLayoutManager(applicationContext)
        recyclerAdapter.adapter=NewsAdapter(newsList)
        recyclerAdapter.setHasFixedSize(true)
        }catch(e:Exception){}



        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navView) as NavHostFragment?
        val navController: NavController? = navHostFragment?.navController

        if (navController != null) {
            viewBinding.navView.setupWithNavController(navController)
        }


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