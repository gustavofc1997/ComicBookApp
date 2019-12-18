package com.sundevs.domain.interactors.base

interface Interactor<Response, Params> where Response : Any {

    suspend operator fun invoke(
        params: Params
    ): Response
}
