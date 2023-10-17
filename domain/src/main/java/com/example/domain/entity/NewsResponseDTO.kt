package com.example.domain.entity


data class NewsResponseDTO(  // Data Transfer Object

    val totalResults: Int? = null,

    val articles: List<ArticlesItemDTO>? = null,


    val status: String? = null
)

data class SourceDTO(


    val name: String? = null,

    val id: String? = null
)


data class ArticlesItemDTO(

    var id: Int? = null,


    val publishedAt: String? = null,


    val author: String? = null,


    val urlToImage: String? = null,


    val description: String? = null,

//    @field:SerializedName("source")
//    val source: Source? = null,


    val title: String? = null,


    val url: String? = null,


    val content: String? = null
)
