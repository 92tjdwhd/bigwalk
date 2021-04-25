package com.bigwalk.test.mvvm.main.campaign.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigwalk.test.R
import com.bigwalk.test.adapter.CampaignListAdapter
import com.bigwalk.test.databinding.FragmentCampaignBinding
import com.bigwalk.test.databinding.FragmentCategoryBinding
import kotlinx.android.synthetic.main.fragment_category.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmptyFragment : Fragment() {
    companion object {
        fun newInstance(): EmptyFragment {
            val fragment = EmptyFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}