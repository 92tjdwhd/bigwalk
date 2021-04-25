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

class CategoryFragment : Fragment() {
    companion object {
        fun newInstance(): CategoryFragment {
            val fragment = CategoryFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel: CategoryFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCategoryBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        with(binding) {
            vm = viewModel
            lifecycleOwner = this@CategoryFragment
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initEvent()
    }

    fun initView() {
        viewModel.requestCampaign()

        rlCampaign.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layout = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layout.findLastCompletelyVisibleItemPosition()
                val lastItem: Int = recyclerView.adapter?.itemCount as Int

                if (lastPosition + 1 == lastItem) {
                    viewModel.requestCampaign()
                }
            }
        })
    }

    fun initEvent() {
        with(viewModel) {
            updateListEvent.observe(requireActivity(), Observer {
                it.getContentIfNotHandled().let {
                    val adapter = rlCampaign.adapter as CampaignListAdapter
                    if (it != null) {
                        adapter.updateData(it)
                    }
                }
            })
        }
    }
}