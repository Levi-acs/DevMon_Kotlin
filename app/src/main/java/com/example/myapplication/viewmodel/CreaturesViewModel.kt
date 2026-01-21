package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.domain.Creature
import com.example.myapplication.model.repository.CreatureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreaturesViewModel @Inject constructor( private val creatureRepository: CreatureRepository) : ViewModel() {

    val creatures = MutableLiveData<List<Creature>>()

    init {
        creatures.value = creatureRepository.creatures
    }

    fun findCreature(number: Int) = creatureRepository.findCreature(number)


}