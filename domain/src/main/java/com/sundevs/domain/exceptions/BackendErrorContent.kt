package com.sundevs.domain.exceptions

data class BackendErrorContent(
    val statusCode: String,
    val error: String,
    val message: String?
)
