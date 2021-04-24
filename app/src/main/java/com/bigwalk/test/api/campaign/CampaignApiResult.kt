package com.bigwalk.test.api.campaign

import com.google.gson.annotations.SerializedName


/**
 * CampaignList DTO(Data Transfer Object) 클래스
 */
class CampaignApiResult {

    @SerializedName("status")
    var status: String = ""

    //@SerializedName 으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함
    @SerializedName("enabled")
    var enabled: Boolean? = null

    @SerializedName("endDate")
    var endDate: String = ""

    @SerializedName("serviceOnTime")
    var serviceOnTime: Boolean? = null

    @SerializedName("remainedDaysToEnd")
    var remainedDaysToEnd: Int = 0

    @SerializedName("ratio")
    var ratio: Int = 0

    @SerializedName("donatedSteps")
    var donatedSteps: Int = 0

    @SerializedName("goalPoint")
    var goalPoint: Int = 0

    @SerializedName("detailThumbnailImagePath")
    var detailThumbnailImagePath: String = ""

    @SerializedName("organizations")
    var organizations: Array<*>? = null

    @SerializedName("largeListThumbnailImagePath")
    var largeListThumbnailImagePath: String? = ""

    @SerializedName("mediumListThumbnailImagePath")
    var mediumListThumbnailImagePath: String? = ""

    @SerializedName("smallListThumbnailImagePath")
    var smallListThumbnailImagePath: String? = ""

    @SerializedName("participantCount")
    var participantCount: Int = 0

    @SerializedName("beneficiary")
    var beneficiary: String = ""

    @SerializedName("beneficiaryLink")
    var beneficiaryLink: String = ""

    @SerializedName("smsId")
    var smsId: Int? = null

    @SerializedName("categoryId")
    var categoryId: Int? = null

    @SerializedName("campaignPromoter")
    var campaignPromoter: CampaignPromoter? = null

    @SerializedName("formattedStartDate")
    var formattedStartDate: String? = null

    @SerializedName("formattedEndDate")
    var formattedEndDate: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("my")
    var my: My? = null


    override fun toString(): String {
        super.toString()
        return "CampaignApiResult{" +
                "status=" + status +
                ", enabled=" + enabled +
                ", endDate=" + endDate +
                ", serviceOnTime=" + serviceOnTime +
                ", remainedDaysToEnd=" + remainedDaysToEnd +
                ", ratio=" + ratio +
                ", donatedSteps=" + donatedSteps +
                ", goalPoint=" + goalPoint +
                ", detailThumbnailImagePath=" + detailThumbnailImagePath +
                ", organizations=" + organizations +
                ", largeListThumbnailImagePath=" + largeListThumbnailImagePath +
                ", mediumListThumbnailImagePath=" + mediumListThumbnailImagePath +
                ", smallListThumbnailImagePath=" + smallListThumbnailImagePath +
                ", participantCount=" + participantCount +
                ", beneficiary=" + beneficiary +
                ", beneficiaryLink=" + beneficiaryLink +
                ", smsId=" + smsId +
                ", categoryId=" + categoryId +
                ", campaignPromoter=" + campaignPromoter.toString() +
                ", formattedStartDate=" + formattedStartDate +
                ", formattedEndDate=" + formattedEndDate +
                ", name=" + name +
                ", id=" + id +
                ", my=" + my.toString() +
                '}';
    }
}

class My() {

    @SerializedName("donatedSteps")
    var donatedSteps: Int? = null

    @SerializedName("ranking")
    var ranking: Int? = null

    @SerializedName("lastDonatedDateTime")
    var lastDonatedDateTime: String? = null

    @SerializedName("story")
    var story: Boolean? = null

    override fun toString(): String {
        super.toString()
        return "My{" +
                ", donatedSteps=" + donatedSteps +
                ", ranking=" + ranking +
                ", lastDonatedDateTime=" + lastDonatedDateTime +
                ", story=" + story +
                '}';
    }
}


class CampaignPromoter() {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int? = null

    override fun toString(): String {
        super.toString()
        return "CampaignPromoter{" +
                ", name=" + name +
                ", id=" + id +
                '}';
    }
}
