package com.bigwalk.test.eventBus

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class CampaignEventBus {

    private val events = MutableSharedFlow<CampaignSortEvent>()

    fun produceEvent(event: CampaignSortEvent) {
        GlobalScope.launch { events.emit(event) }
    }

    suspend fun subscribeEvent(campaignSortEvent: CampaignSortEvent, onEvent: () -> Unit) {
        events.filter { it == campaignSortEvent }.collect { onEvent() }
    }

}
enum class CampaignSortEvent { OpenCampaignEvent, GroupCampaignEvent, HighestParticipation, LowestParticipation,NewestEvent}

