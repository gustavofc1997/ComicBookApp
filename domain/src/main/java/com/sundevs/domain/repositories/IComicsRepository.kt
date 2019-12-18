package com.sundevs.domain.repositories

import com.sundevs.domain.models.Comic

interface IComicsRepository {
    suspend fun getComics(): List<Comic>
}
