package example.com.newgameapp.Models.Game

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Example {
    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("next")
    @Expose
    var next: String? = null

    @SerializedName("previous")
    @Expose
    var previous: Any? = null

    @SerializedName("results")
    @Expose
    var results: List<Result>? = null
    fun getData(): List<Result?>? {
        return results
    }
    @SerializedName("seo_title")
    @Expose
    var seoTitle: String? = null

    @SerializedName("seo_description")
    @Expose
    var seoDescription: String? = null

    @SerializedName("seo_keywords")
    @Expose
    var seoKeywords: String? = null

    @SerializedName("seo_h1")
    @Expose
    var seoH1: String? = null

    @SerializedName("noindex")
    @Expose
    var noindex: Boolean? = null

    @SerializedName("nofollow")
    @Expose
    var nofollow: Boolean? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("filters")
    @Expose
    var filters: Filters? = null

    @SerializedName("nofollow_collections")
    @Expose
    var nofollowCollections: List<String>? = null
    override fun toString(): String {
        return "Example(count=$count, next=$next, previous=$previous, results=$results, seoTitle=$seoTitle, seoDescription=$seoDescription, seoKeywords=$seoKeywords, seoH1=$seoH1, noindex=$noindex, nofollow=$nofollow, description=$description, filters=$filters, nofollowCollections=$nofollowCollections)"
    }

}