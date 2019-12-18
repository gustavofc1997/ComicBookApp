package com.sundevs.data.models

data class APIComic(
    val image: APIComicImage,
    val date_added: String,
    val name: String?,
    val issue_number: String
)