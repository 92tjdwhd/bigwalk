package com.bigwalk.test.mvvm.main


import android.content.DialogInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bigwalk.test.eventBus.CampaignEventBus
import com.bigwalk.test.eventBus.CampaignSortEvent
import com.bigwalk.test.util.Event


class MainViewModel(val campaignEventBus: CampaignEventBus) : ViewModel() {

    val onSettingEvent: MutableLiveData<Event<DialogInterface.OnClickListener>> = MutableLiveData()

    fun onSettingEventListener() {
        val dialogOnClickListener = DialogInterface.OnClickListener { dialog, which ->
            when (which) {
                0 -> campaignEventBus.produceEvent(CampaignSortEvent.OpenCampaignEvent)
                1 -> campaignEventBus.produceEvent(CampaignSortEvent.GroupCampaignEvent)
                2 -> campaignEventBus.produceEvent(CampaignSortEvent.HighestParticipation)
                3 -> campaignEventBus.produceEvent(CampaignSortEvent.LowestParticipation)
                4 -> campaignEventBus.produceEvent(CampaignSortEvent.NewestEvent)
            }
        }
        onSettingEvent.value = Event(dialogOnClickListener)
    }

}