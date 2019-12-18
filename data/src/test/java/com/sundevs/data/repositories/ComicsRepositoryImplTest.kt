package com.sundevs.data.repositories

import com.sundevs.data.base.MockableTest
import com.sundevs.data.mappers.APIComicMapper
import com.sundevs.data.models.APIComic
import com.sundevs.data.models.APIComicImage
import com.sundevs.data.models.APIComicsResponse
import com.sundevs.data.network.NetworkClient
import com.sundevs.data.network.endpoints.ComicService
import com.sundevs.domain.models.Comic
import com.sundevs.domain.repositories.IComicsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ComicsRepositoryImplTest : MockableTest {

    @MockK
    lateinit var comicsService: ComicService
    @MockK
    lateinit var networkClient: NetworkClient
    @MockK
    lateinit var responseAPIComic: Response<APIComicsResponse>

    @Before
    override fun setup() {
        super.setup()
        coEvery {
            responseAPIComic.body()
        }.answers {
            APIComicResponse
        }

        coEvery {
            networkClient.apiCall(comicsService.getComicList())
        }.answers {
            responseAPIComic
        }
    }


    private val APIComicResponse =
        APIComicsResponse(listOf(APIComic(APIComicImage("image"), "2008-06-06 11:09:47", "Comic number", "2")))

    private fun getRepositoryImpl(): IComicsRepository {
        return ComicsRepositoryImpl(networkClient, comicsService)
    }

    @Test
    fun `should get comic list`() {

        val repository = getRepositoryImpl()

        val response: List<Comic> =
            runBlocking {
                repository.getComics()
            }


        coVerify {
            networkClient.apiCall(
                comicsService.getComicList()
            )
        }
        Assert.assertEquals(response.first().name, "Comic number #2")
    }
}