package com.bigwalk.test.mvvm.main.campaign

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigwalk.test.api.campaign.CampaignApi
import com.bigwalk.test.api.campaign.CampaignApiResult
import com.bigwalk.test.util.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class CampaignFragmentViewModel : ViewModel() {

    val myCampaignData: MutableLiveData<ArrayList<CampaignApiResult>> = MutableLiveData()

    fun requestCampaign() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val data = CampaignApi.gatCampaignList(0, 60)

                    myCampaignData.postValue(data)


                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}