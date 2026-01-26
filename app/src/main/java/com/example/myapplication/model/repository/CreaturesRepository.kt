package com.example.myapplication.model.repository

import android.util.Log
import com.example.myapplication.model.domain.Creature
import com.example.myapplication.model.source.remote.CreatureRemoteDataSource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers // ADICIONE
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers // ADICIONE
import io.reactivex.rxjava3.subjects.ReplaySubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreaturesRepository @Inject constructor(remoteDataSource: CreatureRemoteDataSource) {
    val creatures: ReplaySubject<List<Creature>> = ReplaySubject.create(1)

    init {
        remoteDataSource.creatures

            .subscribeOn(Schedulers.io()) // ADICIONE - Executa requisição em background
            .doOnNext {
                Log.d("CREATURES_REPOSITORY", "${it.size} creatures were loaded from API.")
            }

            .doOnError {
                Log.e("CREATURES_REPOSITORY", "Error loading creatures from API.", it)
            }

            .subscribe(
                { creatureList -> // onNext - MUDE PARA FORMATO COMPLETO
                    creatures.onNext(creatureList)
                },
                { error -> // onError - ADICIONE tratamento de erro
                    Log.e("CREATURES_REPOSITORY", "Failed to load creatures", error)
                    // Você pode emitir uma lista vazia como fallback
                    creatures.onNext(emptyList())
                }
            )
    }

    fun findCreature(number: Int): Observable<Creature> = creatures
        .map { list ->
            list.find { it.number == number }!!
        }
}