package com.sundevs.data.models

data class APIComicDetail(
    val image: APIComicImage,
    val character_credits: List<APIComicCredits>?,
    val team_credits: List<APIComicCredits>?,
    val location_credits: List<APIComicCredits>?
)