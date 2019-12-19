package com.sundevs.data.mappers

import com.sundevs.data.base.MockableTest
import com.sundevs.data.models.APIComicCredits
import com.sundevs.data.models.APIComicDetail
import com.sundevs.data.models.APIComicImage
import org.junit.Assert
import org.junit.Test


class APIComicDetailMapperTest : APIComicDetailMapperDependentTest() {

    @Test
    fun shouldBeAbleToMapFromAPIComicDetail() {
        val comicDetail = APIComicDetailMapper.map(apiComicDetail)
        Assert.assertNotNull(comicDetail)
        Assert.assertEquals("image", comicDetail.image)
        Assert.assertEquals("NAME_CHARACTER", comicDetail.characterCredits?.first()?.name)
        Assert.assertEquals("NAME_TEAM", comicDetail.teamCredits?.first()?.name)
        Assert.assertEquals("NAME_LOCATION", comicDetail.locationCredits?.first()?.name)

    }
}


abstract class APIComicDetailMapperDependentTest : MockableTest {

    val apiComicDetail = APIComicDetail(
        APIComicImage("image"),
        listOf(APIComicCredits("NAME_CHARACTER")),
        listOf(APIComicCredits("NAME_TEAM")),
        listOf(APIComicCredits("NAME_LOCATION"))
    )
}
