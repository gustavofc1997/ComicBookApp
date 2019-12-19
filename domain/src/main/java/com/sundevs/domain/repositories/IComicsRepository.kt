package com.sundevs.domain.repositories

import com.sundevs.domain.models.Comic
import com.sundevs.domain.models.ComicDetail

interface IComicsRepository {
    suspend fun getComics(): List<Comic>
    suspend fun getComicDetail( url: String): ComicDetail
}
