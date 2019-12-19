package com.sundevs.data.mappers

import com.sundevs.data.base.MockableTest
import com.sundevs.data.models.APIComic
import com.sundevs.data.models.APIComicImage
import org.junit.Assert
import org.junit.Test

class APIComicImageMapperTest : APIComicImageMapperDependentTest() {

    @Test
    fun shouldBeAbleToMapFromAPIComicImage() {
        val comic = APIImageMapper.map(apiComic)
        Assert.assertNotNull(comic)
        Assert.assertEquals("image", comic)
    }
}


abstract class APIComicImageMapperDependentTest : MockableTest {

    val apiComic = APIComicImage("image")
}
