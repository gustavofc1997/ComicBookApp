package com.sundevs.domain.interactors

import com.sundevs.domain.base.MockableTest
import com.sundevs.domain.models.Comic
import com.sundevs.domain.models.ComicDetail
import com.sundevs.domain.models.None
import com.sundevs.domain.repositories.IComicsRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetComicDetailInteractorTest : MockableTest {


    @MockK
    lateinit var comicsRepository: IComicsRepository

    private val comic = ComicDetail("image_url", listOf(), listOf(), listOf())

    @Before
    override fun setup() {
        super.setup()
        coEvery { comicsRepository.getComicDetail(any()) }.answers {
            comic
        }
    }

    @Test
    fun `get comic detail`() {
        val interactor =
            GetComicDetailInteractor(comicsRepository)
        val result =
            runBlocking {
                interactor("anyString")
            }

        Assert.assertEquals("image_url", result.image)
    }
}