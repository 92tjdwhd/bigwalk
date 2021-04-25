package com.bigwalk.test.mvvm.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigwalk.test.R
import com.bigwalk.test.adapter.MainViewPage2Adapter
import com.bigwalk.test.api.campaign.CampaignApi
import com.bigwalk.test.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(binding) {
            vm = viewModel
            lifecycleOwner = this@MainActivity
        }

        initView()
        initEvent()
    }

    private fun initView(){
        vp2Main.adapter = MainViewPage2Adapter(supportFragmentManager,lifecycle)
        TabLayoutMediator(tlActionbar, vp2Main, true, true, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            when(position){
                0 -> tab.text = resources.getString(R.string.campaign)
                1 -> tab.text = resources.getString(R.string.post)
            }
        }
        ).attach()

        vp2Main.isUserInputEnabled = false

        CoroutineScope(Dispatchers.IO).launch {
            try{
                val data =  CampaignApi.gatCampaignList(0,60)
                Log.d("tesetsdagagsfd",data[0].toString())
            }catch (e :Exception){
                e.printStackTrace()
            }
        }
    }

    private fun initEvent(){

    }
}