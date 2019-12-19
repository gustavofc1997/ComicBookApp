package com.sundevs.data.repositories

import com.sundevs.data.mappers.APIComicDetailMapper
import com.sundevs.data.mappers.APIComicMapper
import com.sundevs.data.network.NetworkClient
import com.sundevs.data.network.endpoints.ComicService
import com.sundevs.domain.exceptions.ComicsNotFoundException
import com.sundevs.domain.models.Comic
import com.sundevs.domain.models.ComicDetail
import com.sundevs.domain.repositories.IComicsRepository

class ComicsRepositoryImpl(
    private val networkClient: NetworkClient,
    private val comicsService: ComicService
) : IComicsRepository {
    override suspend fun getComicDetail(url: String): ComicDetail {
        val response = networkClient.apiCall(comicsService.getComicDetail(url)).body()?.let {
            it
        } ?: throw ComicsNotFoundException()
        return APIComicDetailMapper.map(response.results)
    }

    override suspend fun getComics(): List<Comic> {
        val response = networkClient.apiCall(comicsService.getComicList()).body()?.let {
            it
        } ?: throw ComicsNotFoundException()
        return response.results.map {
            APIComicMapper.map(it)
        }
    }
}