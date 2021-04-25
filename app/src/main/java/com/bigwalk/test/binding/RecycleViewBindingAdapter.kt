package com.bigwalk.test.binding


import android.annotation.SuppressLint
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigwalk.test.adapter.CampaignListAdapter
import com.bigwalk.test.api.campaign.CampaignApiResult

import java.lang.Exception


object RecycleViewBindingAdapter {

    @SuppressLint("WrongConstant")
    @JvmStatic
    @BindingAdapter("setCategoryItems")
    fun setCategoryInit(view: RecyclerView, item: ArrayList<CampaignApiResult>?) {
        val linearLayoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)
        view.layoutManager = linearLayoutManager
        view.adapter = item?.let { CampaignListAdapter(it) }
        view.adapter?.notifyDataSetChanged()
    }
}