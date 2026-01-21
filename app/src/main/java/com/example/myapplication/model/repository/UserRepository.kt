package com.example.myapplication.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.domain.Creature
import com.example.myapplication.model.domain.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val creatureRepository: CreatureRepository) {
    val user = User("Paulo Salvatore", true)

        private val _onChooseCreature = MutableLiveData<Creature>()
        val onChooseCreature : LiveData<Creature>
        get() = _onChooseCreature




    fun chooseCreature() {
        if (!user.hasCreatureAvailable) {
            return
        }

        user.hasCreatureAvailable = false
        val randoCreature = creatureRepository.creatures.random()
        user.creatures.add(randoCreature)

        _onChooseCreature.value = randoCreature


    }
}