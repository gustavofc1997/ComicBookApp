package com.sundevs.data.mappers

import com.sundevs.data.models.APIComic
import com.sundevs.data.models.APIComicCredits
import com.sundevs.data.models.APIComicDetail
import com.sundevs.data.utils.EMPTY
import com.sundevs.data.utils.convertDateFromApiToAppFormat
import com.sundevs.domain.models.Comic
import com.sundevs.domain.models.ComicCredit
import com.sundevs.domain.models.ComicDetail

object APIComicMapper {
    fun map(apiComic: APIComic): Comic {
        val issueNumber = EMPTY + apiComic.issue_number
        return Comic(
            apiComic.name?.plus(issueNumber) ?: "Name not found $issueNumber",
            APIImageMapper.map(apiComic.image),
            apiComic.date_added.convertDateFromApiToAppFormat(), apiComic.api_detail_url
        )
    }
}

object APIComicCreditMapper {
    fun map(apiComicCredit: APIComicCredits): ComicCredit {
        return ComicCredit(apiComicCredit.name)
    }
}

object APIComicDetailMapper {
    fun map(apiComicDetail: APIComicDetail): ComicDetail {
        return ComicDetail(APIImageMapper.map(apiComicDetail.image),
            apiComicDetail.character_credits?.map { APIComicCreditMapper.map(it) },
            apiComicDetail.location_credits?.map { APIComicCreditMapper.map(it) },
            apiComicDetail.team_credits?.map { APIComicCreditMapper.map(it) })
    }
}