package com.bigwalk.test.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bigwalk.test.mvvm.main.campaign.category.CategoryFragment
import com.bigwalk.test.mvvm.main.campaign.category.EmptyFragment

class MainCampaignCategoryViewPage2Adapter(fragmentManager:FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CategoryFragment.newInstance()
            else ->  EmptyFragment.newInstance()
        }

    }
}