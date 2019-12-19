package com.sundevs.domain.models

data class ComicDetail(
    val image:String?,
    val characterCredits: List<ComicCredit>?,
    val locationCredits: List<ComicCredit>?,
    val teamCredits: List<ComicCredit>?
)