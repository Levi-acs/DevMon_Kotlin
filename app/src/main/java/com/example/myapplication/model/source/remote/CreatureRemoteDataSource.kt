package com.example.myapplication.model.source.remote

import com.example.myapplication.model.domain.Creature
import com.example.myapplication.model.source.remote.entity.CreatureApiResponse
import com.example.myapplication.model.source.remote.service.CreatureService
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CreatureRemoteDataSource @Inject constructor() {
    private val service: CreatureService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://devmon-api.onrender.com/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(CreatureService::class.java)

    }

    val creatures: Observable<List<Creature>> =
        service.findAll().map { list ->
            list.map { it.toDomain() }
        }

    private fun CreatureApiResponse.toDomain(): Creature {
        return Creature(
            number = number,
            name = name,
            imageUrl = image,
        )


    }
}