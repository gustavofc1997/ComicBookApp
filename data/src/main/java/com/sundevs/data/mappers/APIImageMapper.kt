package com.sundevs.data.mappers

import com.sundevs.data.models.APIComicImage

object APIImageMapper {
    fun map(image: APIComicImage) = image.original_url
}