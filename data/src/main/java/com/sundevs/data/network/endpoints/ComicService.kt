package com.sundevs.data.network.endpoints

import com.sundevs.data.models.APIComicsDetailResponse
import com.sundevs.data.models.APIComicsResponse
import com.sundevs.data.utils.GET_COMIC_LIST
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ComicService {

    @GET(GET_COMIC_LIST)
    fun getComicList(): Deferred<Response<APIComicsResponse>>

    @GET
    fun getComicDetail(@Url url: String): Deferred<Response<APIComicsDetailResponse>>
}