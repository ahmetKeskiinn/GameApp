package example.com.newgameapp.Models.Game

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Store {
    @SerializedName("id")
    @Expose
    var ıd: Int? = null

    @SerializedName("store")
    @Expose
    var store: Store__1? = null

}