package com.bigwalk.test.mvvm.main.campaign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bigwalk.test.R
import com.bigwalk.test.adapter.MainCampaignCategoryViewPage2Adapter
import com.bigwalk.test.adapter.MainViewPage2Adapter
import com.bigwalk.test.databinding.FragmentCampaignBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_campaign.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CampaignFragment : Fragment() {
    companion object {
        fun newInstance(): CampaignFragment {
            val fragment = CampaignFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

   private  val viewModel : CampaignFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCampaignBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_campaign, container, false)
        with(binding) {
            vm = viewModel
            lifecycleOwner = this@CampaignFragment
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView(){
        vp2Category.adapter = MainCampaignCategoryViewPage2Adapter(parentFragmentManager,lifecycle)
        TabLayoutMediator(tab_layout, vp2Category, true, true, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            when(position){
                0 -> tab.text = resources.getString(R.string.all)
            }
        }
        ).attach()

        viewModel.requestCampaign()
    }


}