package com.bigwalk.test.mvvm.main.campaign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bigwalk.test.R
import com.bigwalk.test.databinding.FragmentCampaignBinding
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
    }
}