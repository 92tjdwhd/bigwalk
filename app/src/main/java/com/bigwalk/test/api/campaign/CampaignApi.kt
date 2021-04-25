package com.bigwalk.test.api.campaign


import android.util.Log
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Logger

object CampaignApi {

    val hostURLAddress = "https://app-dev.bigwalk.co.kr:10000"

    val httpClient: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor { Log.d("sseLogger", "NetworkLog='$it'") }.setLevel(
            HttpLoggingInterceptor.Level.BODY
        )
    ).addInterceptor { chain ->
        val original: Request = chain.request()
        val request: Request = original.newBuilder()
            .header(
                "X-AUTH-TOKEN",
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxODUiLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjExNTYzMzgxLCJleHAiOjE3MDYxNzEzODF9._4DPRRFx09yIBVLqwbTGVSuP6vy5fM4UP3vJXszfP4w"
            )
            .method(original.method(), original.body())
            .build()
        Log.d("ApiRequest", "request ==>${request.body().toString()}")
        Log.d("ApiRequest", "request ==>${request.url()}")

        chain.proceed(request)
    }
    val client = httpClient.build()

    val retrofitCampaign by lazy {
        Retrofit.Builder()
            .baseUrl(hostURLAddress)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CampaignService::class.java)
    }

    /**
     * url 파서 정보
     */
    suspend fun gatCampaignList(page: Int, size: Int): ArrayList<CampaignApiResult> {
        return retrofitCampaign.getCampaignList(page, size)
    }
}