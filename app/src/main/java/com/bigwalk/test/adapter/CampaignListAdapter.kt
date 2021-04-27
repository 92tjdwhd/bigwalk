package com.bigwalk.test.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bigwalk.test.R
import com.bigwalk.test.api.campaign.CampaignApiResult
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.campaign_list.view.*
import java.util.*
import kotlin.collections.ArrayList

class CampaignListAdapter(var data: ArrayList<CampaignApiResult>) :
    RecyclerView.Adapter<CampaignListAdapter.CampaignListAdapterViewHolder>() {

    private val tempData: ArrayList<CampaignApiResult> by lazy {
        data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CampaignListAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.campaign_list, parent, false)
        return CampaignListAdapterViewHolder(view)
    }


    fun updateData(list: ArrayList<CampaignApiResult>) {
        //data.addAll(list)
        // notifyDataSetChanged()
    }

    fun filterList(isOpen: Boolean) {
        data = if (isOpen) {
            tempData.filter { it.organizations?.size ?: 0 == 1 } as ArrayList<CampaignApiResult>
        } else {
            tempData.filter { it.organizations?.size ?: 0 >= 1 } as ArrayList<CampaignApiResult>
        }
        notifyDataSetChanged()
    }

    fun participantSort(isHigh: Boolean) {
        if (isHigh) {
            data = tempData.sortedByDescending { it.participantCount }.toCollection(ArrayList())
        } else {
            data = tempData.sortedBy { it.participantCount }.toCollection(ArrayList())
        }
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: CampaignListAdapterViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class CampaignListAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val ivCampaignThumbnail = view.ivCampaignThumbnail
        val tvCampaignTitle = view.tvCampaignTitle
        val tvCompany = view.tvCompany
        val tvBadge = view.tvBadge
        val tvPercent = view.tvPercent
        val tvStory = view.tvStory
        val pbCampaign = view.pbCampaign
        val ivAttendIcon = view.ivAttendIcon

        val radius = view.context.resources.getDimension(R.dimen.campaign_category_thumbnail)
        val requestManager = Glide.with(view.context)

        fun bind(campaign: CampaignApiResult) {
            tvCampaignTitle.text = campaign.name
            tvCompany.text = campaign.campaignPromoter?.name
            tvBadge.text = if (campaign.organizations?.isEmpty() != false) "공개형" else "그룹형"
            tvPercent.text = "${campaign.ratio}%"
            pbCampaign.progress = campaign.ratio
            val story = campaign.my?.story ?: false
            if (story) {
                tvStory.text = "기부완료"
            } else {
                tvStory.text = "종료"
            }

            requestManager.load(campaign.smallListThumbnailImagePath)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(radius.toInt())))
                .into(ivCampaignThumbnail)

        }

    }
}