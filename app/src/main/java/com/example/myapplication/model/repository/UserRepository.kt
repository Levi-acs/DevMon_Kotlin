package com.example.myapplication.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.domain.Creature
import com.example.myapplication.model.domain.User
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val creaturesRepository: CreaturesRepository) {
    val user = User("Paulo Salvatore", true)

    val allCreatures: Observable<List<Creature>>
        get() = creaturesRepository.creatures.map { list ->
            list.map {
                val isKnown = user.creatures.any { creatureOwnByUser ->
                    creatureOwnByUser.number == it.number
                }

                it.copy(
                    name = if (isKnown) it.name else "?????",
                    isKnown = isKnown
                )
            }
        }



    private val _onChooseCreature = MutableLiveData<Creature>()
    val onChooseCreature: LiveData<Creature>
        get() = _onChooseCreature


    fun chooseCreature(): Observable<Creature> =
        Observable.just(user)
            .filter {
                it.hasCreatureAvailable
            }
            .doOnNext{
                it.hasCreatureAvailable = false
            }
            .flatMap{
                creaturesRepository.creatures
            }
            .map{
                    list ->
                list.randomOrNull()
                    ?: throw IllegalStateException("Lista de criaturas está vazia")
            }
            .doOnNext{
                user.creatures.add(it)
            }
            .doOnNext{
                _onChooseCreature.postValue(it)
            }


}