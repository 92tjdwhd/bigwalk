package com.bigwalk.test.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bigwalk.test.R
import com.bigwalk.test.api.campaign.CampaignApiResult
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.campaign_my_list.view.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class CampaignMyListAdapter(val data: ArrayList<CampaignApiResult>) :
    RecyclerView.Adapter<CampaignMyListAdapter.CampaignMyListAdapterViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CampaignMyListAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.campaign_my_list, parent, false)
        return CampaignMyListAdapterViewHolder(view)
    }



    fun updateData(list: ArrayList<CampaignApiResult>) {
        data.addAll(list)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: CampaignMyListAdapterViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class CampaignMyListAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val ivCampaignThumbnail = view.ivCampaignThumbnail
        val ivEndCampaign = view.ivEndCampaign
        val tvCampaignTitle = view.tvCampaignTitle
        val tvPercent = view.tvPercent
        val pbCampaign = view.pbCampaign

        val ivResultPost = view.ivResultPost

        val radius = view.context.resources.getDimension(R.dimen.campaign_category_thumbnail)
        val requestManager = Glide.with(view.context)

        val nowDT = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")


        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(campaign: CampaignApiResult) {
            tvCampaignTitle.text = campaign.name
            tvPercent.text = "${campaign.ratio}%"
            pbCampaign.progress = campaign.ratio

            val story = campaign.my?.story?:false
            val endDT = formatter.parse(campaign.endDate)
            if(nowDT.before(endDT)){
                ivEndCampaign.visibility = View.GONE

            }else{
                ivEndCampaign.visibility = View.VISIBLE
            }

            if(story){
                ivResultPost.visibility = View.VISIBLE
            }else{
                ivResultPost.visibility = View.GONE
            }

            requestManager.load(campaign.smallListThumbnailImagePath).apply(RequestOptions.bitmapTransform(RoundedCorners(radius.toInt()))).into(ivCampaignThumbnail)

        }

    }
}