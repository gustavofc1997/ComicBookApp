package com.sundevs.domain.interactors

import com.sundevs.domain.interactors.base.Interactor
import com.sundevs.domain.models.Comic
import com.sundevs.domain.models.ComicDetail
import com.sundevs.domain.models.None
import com.sundevs.domain.repositories.IComicsRepository

class GetComicDetailInteractor(private val comicsRepository: IComicsRepository) :
    Interactor<ComicDetail, String> {
    override suspend fun invoke(params: String): ComicDetail {
        return comicsRepository.getComicDetail(params)
    }
}