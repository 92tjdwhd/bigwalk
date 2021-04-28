package com.bigwalk.test.api.campaign


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CampaignService {
    @GET("api/campaigns/category/0/story?")
    suspend fun getCampaignList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ArrayList<CampaignApiResult>
}