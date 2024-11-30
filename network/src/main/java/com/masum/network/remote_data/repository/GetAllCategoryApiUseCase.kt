package com.masum.network.remote_data.repository

import com.masum.network.data_object_model.WeatherDto

interface GetAllCategoryApiUseCase {
    suspend operator fun invoke(lat:Double,lng:Double): Result<WeatherDto>
}


class GetAllCategoryApiUseCaseImpl(private val repository: NetworkDataRepository) :
    GetAllCategoryApiUseCase {

    override suspend fun invoke(lat:Double,lng:Double): Result<WeatherDto> {
        return repository.getAllCategory(lat,lng)
    }

}