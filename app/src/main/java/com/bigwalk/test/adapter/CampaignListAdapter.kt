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
        val ivEndCampaign = view.ivEndCampaign
        val tvCampaignTitle = view.tvCampaignTitle
        val tvCompany = view.tvCompany
        val tvBadge = view.tvBadge
        val tvPercent = view.tvPercent
        val tvStory = view.tvStory
        val pbCampaign = view.pbCampaign
        val ivAttendIcon = view.ivAttendIcon
        val ivResultPost = view.ivResultPost

        val radius = view.context.resources.getDimension(R.dimen.campaign_category_thumbnail)
        val requestManager = Glide.with(view.context)

        val nowDT = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")


        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(campaign: CampaignApiResult) {
            tvCampaignTitle.text = campaign.name
            tvCompany.text = campaign.campaignPromoter?.name
            tvPercent.text = "${campaign.ratio}%"
            pbCampaign.progress = campaign.ratio

            val story = campaign.my?.story?:false
            val endDT = formatter.parse(campaign.endDate)
            if(nowDT.before(endDT)){
                tvStory.text = itemView.context.getText(R.string.proceeding)
                ivEndCampaign.visibility = View.GONE
                tvCampaignTitle.setTextColor(itemView.context.getColor(R.color.campaignBaseColor))
                tvCompany.setTextColor(itemView.context.getColor(R.color.campaignCompanyColor))
            }else{
                tvStory.text = itemView.context.getText(R.string.end)
                ivEndCampaign.visibility = View.VISIBLE
                tvCampaignTitle.setTextColor(itemView.context.getColor(R.color.campaignDisableColor))
                tvCompany.setTextColor(itemView.context.getColor(R.color.campaignDisableColor))
            }

            if(campaign.organizations?.size?:0 < 1){
                tvBadge.text = itemView.context.getText(R.string.campaign_open)
                tvBadge.background = itemView.context.getDrawable(R.drawable.bg_campaign_open_badge)
            }else{
                tvBadge.text = itemView.context.getText(R.string.campaign_group)
                tvBadge.background = itemView.context.getDrawable(R.drawable.bg_campaign_group_badge)
            }
            tvBadge.text = if(campaign.organizations?.size?:0 < 1) "공개형" else "그룹형"

            if(story){
                ivResultPost.visibility = View.VISIBLE
            }else{
                ivResultPost.visibility = View.GONE
            }

            requestManager.load(campaign.smallListThumbnailImagePath).apply(RequestOptions.bitmapTransform(RoundedCorners(radius.toInt()))).into(ivCampaignThumbnail)

        }

    }
}