package com.sundevs.data.base

import io.mockk.MockKAnnotations
import org.junit.Before

interface MockableTest {

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }
}