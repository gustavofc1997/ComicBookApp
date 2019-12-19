package com.sundevs.data.mappers

import com.sundevs.data.base.MockableTest
import com.sundevs.data.models.APIComicCredits
import org.junit.Assert
import org.junit.Test

class APIComicCreditMapperTest : APIComicCreditMapperDependentTest() {

    @Test
    fun shouldMapFromAPIComicCredit() {
        val credit = APIComicCreditMapper.map(apiComicCredit)
        Assert.assertNotNull(credit)
        Assert.assertEquals("thor", credit.name)
    }
}

abstract class APIComicCreditMapperDependentTest : MockableTest {

    val apiComicCredit = APIComicCredits("thor")
}
