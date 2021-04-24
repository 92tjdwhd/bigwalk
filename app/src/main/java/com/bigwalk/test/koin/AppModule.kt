package com.bigwalk.test.koin

import com.bigwalk.test.mvvm.main.MainViewModel
import com.bigwalk.test.mvvm.main.campaign.CampaignFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel() }

    viewModel { CampaignFragmentViewModel() }
}