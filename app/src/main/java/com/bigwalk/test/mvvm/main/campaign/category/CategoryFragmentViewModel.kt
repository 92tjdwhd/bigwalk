package com.bigwalk.test.mvvm.main.campaign.category

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

class CategoryFragmentViewModel : ViewModel() {

    val categoryData:MutableLiveData<ArrayList<CampaignApiResult>> = MutableLiveData()

    val updateListEvent:MutableLiveData<Event<ArrayList<CampaignApiResult>>> = MutableLiveData()


    var pagingNum = 0

    fun requestCampaign() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val data = CampaignApi.gatCampaignList(pagingNum, 20)
                    if(pagingNum > 0){
                        updateListEvent.postValue(Event(data))
                        pagingNum++
                    }else{
                        categoryData.postValue(data)
                        pagingNum++
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}