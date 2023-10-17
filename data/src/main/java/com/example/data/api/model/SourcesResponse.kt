package com.example.data.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entity.SourcesResponseDTO
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

fun <T> Any.convertTo(clazz: Class<T>): T {
    val gson = Gson()
    val jsonString = gson.toJson(this)
    return gson.fromJson(jsonString, clazz)
}

data class SourcesResponse(

    @field:SerializedName("sources")
    val sources: List<SourcesItem>? = null,

    @field:SerializedName("status")
    val status: String? = null
) {
    fun toSourcesResponseDTO(): SourcesResponseDTO {
        val gson = Gson()
        val jsonString = gson.toJson(this)
        return gson.fromJson(jsonString, SourcesResponseDTO::class.java)
    }
}

@Entity
data class SourcesItem(

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("language")
    val language: String? = null,
    @PrimaryKey
    @field:SerializedName("id")
    var id: String,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)
