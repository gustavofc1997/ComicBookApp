package com.sundevs.data.repositories

import com.sundevs.data.base.MockableTest
import com.sundevs.data.mappers.APIComicMapper
import com.sundevs.data.models.*
import com.sundevs.data.network.NetworkClient
import com.sundevs.data.network.endpoints.ComicService
import com.sundevs.domain.models.Comic
import com.sundevs.domain.models.ComicDetail
import com.sundevs.domain.repositories.IComicsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import retrofit2.Response

class ComicsRepositoryImplTest : MockableTest {

    @MockK
    lateinit var comicsService: ComicService
    @MockK
    lateinit var networkClient: NetworkClient
    @MockK
    lateinit var responseAPIComic: Response<APIComicsResponse>
    @MockK
    lateinit var responseAPIComicDetails: Response<APIComicsDetailResponse>

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

        coEvery {
            networkClient.apiCall(comicsService.getComicDetail(anyString()))
        }.answers {
            responseAPIComicDetails
        }

        coEvery {
            responseAPIComicDetails.body()
        }.answers {
            APIComicsDetailResponse
        }
    }

    private val APIComicResponse =
        APIComicsResponse(
            listOf(
                APIComic(
                    APIComicImage("image"),
                    "2008-06-06 11:09:47",
                    "Comic number",
                    "2",
                    "URL"
                )
            )
        )

    private val APIComicDetail = APIComicDetail(
        APIComicImage("image"),
        listOf(APIComicCredits("name_character")),
        listOf(APIComicCredits("name_team")),
        listOf(APIComicCredits("name_location"))
    )

    private val APIComicsDetailResponse =
        APIComicsDetailResponse(APIComicDetail)

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

    @Test
    fun `should get comic detail`() {
        val repository = getRepositoryImpl()
        val response: ComicDetail =
            runBlocking {
                repository.getComicDetail(anyString())
            }

        coVerify {
            networkClient.apiCall(
                comicsService.getComicDetail(anyString())
            )
        }

        Assert.assertEquals(response.image, "image")
        Assert.assertEquals(response.teamCredits?.first()?.name, "name_team")
        Assert.assertEquals(response.characterCredits?.first()?.name, "name_character")
        Assert.assertEquals(response.locationCredits?.first()?.name, "name_location")
    }
}