package com.example.domain.entity

data class SourcesResponseDTO(


    val sources: List<SourcesItemDTO>? = null,

    val status: String? = null
)


data class SourcesItemDTO(

    val country: String? = null,


    val name: String? = null,

    val description: String? = null,


    val language: String? = null,

    var id: String,

    val category: String? = null,

    val url: String? = null
)
