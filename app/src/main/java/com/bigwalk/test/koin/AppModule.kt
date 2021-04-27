package com.bigwalk.test.koin

import com.bigwalk.test.eventBus.CampaignEventBus
import com.bigwalk.test.mvvm.login.LoginViewModel
import com.bigwalk.test.mvvm.main.MainViewModel
import com.bigwalk.test.mvvm.main.campaign.CampaignFragmentViewModel
import com.bigwalk.test.mvvm.main.campaign.category.CategoryFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { CampaignEventBus() }

    viewModel { MainViewModel(get()) }

    viewModel { LoginViewModel() }

    viewModel { CampaignFragmentViewModel() }

    viewModel { CategoryFragmentViewModel(get()) }
}