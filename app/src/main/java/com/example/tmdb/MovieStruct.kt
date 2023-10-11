package com.example.example

import com.google.gson.annotations.SerializedName


data class MovieStruct(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<MoviesResponces> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null

)