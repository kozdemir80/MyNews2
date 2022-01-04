package com.example.mynews2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mynews2.View.Second_Fragment
import com.example.mynews2.View.Third_Fragment


class ViewPagerAdapter(FragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(FragmentManager,lifecycle){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
    return  when(position){
          0->{
              first_Fragment()
          }
          1->{
              Second_Fragment()
          }
         2->{
             Third_Fragment()
         }
          else->{
              Fragment()
          }
      }
    }
}