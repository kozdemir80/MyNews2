package com.example.mynews2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mynews2.View.Most_Popular
import com.example.mynews2.View.Business_Fragment
import com.example.mynews2.View.Top_Stories


class ViewPagerAdapter(FragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(FragmentManager,lifecycle){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
    return  when(position){
          0->{
              Top_Stories()
          }
          1->{
              Most_Popular()
          }
         2-> {
             Business_Fragment()
         }

          else->{
              Fragment()
          }
      }
    }
}