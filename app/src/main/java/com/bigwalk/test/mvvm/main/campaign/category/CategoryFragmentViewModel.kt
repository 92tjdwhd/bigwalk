package com.bigwalk.test.mvvm.main.campaign.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigwalk.test.api.campaign.CampaignApi
import com.bigwalk.test.api.campaign.CampaignApiResult
import com.bigwalk.test.eventBus.CampaignEventBus
import com.bigwalk.test.eventBus.CampaignSortEvent
import com.bigwalk.test.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class CategoryFragmentViewModel(val campaignEventBus: CampaignEventBus) : ViewModel() {

    val categoryData: MutableLiveData<ArrayList<CampaignApiResult>> = MutableLiveData()

    val updateListEvent: MutableLiveData<Event<ArrayList<CampaignApiResult>>> = MutableLiveData()

    val campaignSortEvent: MutableLiveData<Event<CampaignSortEvent>> = MutableLiveData()

    var pagingNum = 0

    fun initEventBusSubscribe() {
        viewModelScope.launch {
            campaignEventBus.subscribeEvent(CampaignSortEvent.OpenCampaignEvent) {
                campaignSortEvent.value = Event(CampaignSortEvent.OpenCampaignEvent)
            }
        }
        viewModelScope.launch {
            campaignEventBus.subscribeEvent(CampaignSortEvent.GroupCampaignEvent) {
                campaignSortEvent.value = Event(CampaignSortEvent.GroupCampaignEvent)
            }
        }
        viewModelScope.launch {
            campaignEventBus.subscribeEvent(CampaignSortEvent.HighestParticipation) {
                campaignSortEvent.value = Event(CampaignSortEvent.HighestParticipation)
            }
        }
        viewModelScope.launch {
            campaignEventBus.subscribeEvent(CampaignSortEvent.LowestParticipation) {
                campaignSortEvent.value = Event(CampaignSortEvent.LowestParticipation)
            }
        }
        viewModelScope.launch {
            campaignEventBus.subscribeEvent(CampaignSortEvent.NewestEvent) {
                campaignSortEvent.value = Event(CampaignSortEvent.NewestEvent)
            }
        }

    }


    fun requestCampaign() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val data = CampaignApi.gatCampaignList(pagingNum, 20)
                    if (pagingNum > 0) {
                        updateListEvent.postValue(Event(data))
                        pagingNum++
                    } else {
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