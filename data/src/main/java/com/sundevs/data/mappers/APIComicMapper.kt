package com.sundevs.data.mappers

import com.sundevs.data.models.APIComic
import com.sundevs.data.utils.EMPTY
import com.sundevs.data.utils.convertDateFromApiToAppFormat
import com.sundevs.domain.models.Comic

object APIComicMapper {
    fun map(apiComic: APIComic): Comic {
        val issueNumber = EMPTY + apiComic.issue_number
        return Comic(
            apiComic.name?.plus(issueNumber) ?: "Name not found $issueNumber",
            APIImageMapper.map(apiComic.image),
            apiComic.date_added.convertDateFromApiToAppFormat()
        )
    }
}

