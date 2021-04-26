package com.bigwalk.test.binding


import android.annotation.SuppressLint
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigwalk.test.adapter.CampaignListAdapter
import com.bigwalk.test.adapter.CampaignMyListAdapter
import com.bigwalk.test.api.campaign.CampaignApiResult

import java.lang.Exception


object RecycleViewBindingAdapter {


    @JvmStatic
    @BindingAdapter("setCategoryItems")
    fun setCategoryInit(view: RecyclerView, item: ArrayList<CampaignApiResult>?) {
        val linearLayoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        view.layoutManager = linearLayoutManager
        view.adapter = item?.let { CampaignListAdapter(it) }
        view.adapter?.notifyDataSetChanged()
    }


    @JvmStatic
    @BindingAdapter("setMyCampaignItems")
    fun setMyCampaignList(view: RecyclerView, item: ArrayList<CampaignApiResult>?) {
        val linearLayoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        view.layoutManager = linearLayoutManager
        view.adapter = item?.let { CampaignMyListAdapter(it) }
        view.adapter?.notifyDataSetChanged()
    }
}