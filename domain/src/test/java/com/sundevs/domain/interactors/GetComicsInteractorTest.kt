package com.sundevs.domain.interactors

import com.sundevs.domain.base.MockableTest
import com.sundevs.domain.models.Comic
import com.sundevs.domain.models.None
import com.sundevs.domain.repositories.IComicsRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetComicsInteractorTest : MockableTest {

    @MockK
    lateinit var comicsRepository: IComicsRepository

    private val comic = Comic("name", "", "")

    @Before
    override fun setup() {
        super.setup()
        coEvery { comicsRepository.getComics() }.answers {
            listOf(comic)
        }
    }

    @Test
    fun `get comics`() {
        val interactor =
            GetComicsInteractor(comicsRepository)
        val result =
            runBlocking {
                interactor(None)
            }

        Assert.assertEquals(listOf(comic), result)

    }
}