package com.pop_flake.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieModel(
    val country: String,
    val end_date: Any,
    val id: Int?= null,
    val image_thumbnail_path: String,
    @SerializedName("name")
    val name: String?= null,
    val network: String,
    val permalink: String,
    val start_date: String,
    val status: String
): Serializable {

    override fun hashCode(): Int {
        var result = id.hashCode()
        if(name.isNullOrEmpty()){
            result = 31 * result + name.hashCode()
        }
        return result
    }

}
