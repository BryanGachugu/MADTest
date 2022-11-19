package com.ics.mad.model.data

data class CodingResource(
    val id: Int,
    val description: String,
    val url: String,
    val types: List<String>,
    val topics: List<String>,
    val levels: List<String>,
)