package com.sundevs.data.mappers

import com.sundevs.data.base.MockableTest
import com.sundevs.data.models.APIComic
import com.sundevs.data.models.APIComicImage
import org.junit.Assert
import org.junit.Test

class APIComicMapperTest : APIComicMapperDependentTest() {
    @Test
    fun shouldBeAbleToMapFromAPIComic() {
        val comic = APIComicMapper.map(apiComic)
        Assert.assertNotNull(comic)
        Assert.assertEquals("Comic one #1", comic.name)
    }
}

abstract class APIComicMapperDependentTest : MockableTest {

    val apiComic = APIComic(APIComicImage("image"), "2008-06-06 11:09:47", "Comic one", "1")
}
