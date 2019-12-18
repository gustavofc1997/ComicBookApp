package com.sundevs.domain.interactors

import com.sundevs.domain.interactors.base.Interactor
import com.sundevs.domain.models.Comic
import com.sundevs.domain.models.None
import com.sundevs.domain.repositories.IComicsRepository

class GetComicsInteractor(private val comicsRepository: IComicsRepository) :
    Interactor<List<Comic>, None> {
    override suspend fun invoke(params: None): List<Comic> {
        return comicsRepository.getComics()
    }
}