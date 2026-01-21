package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.domain.Creature
import com.example.myapplication.model.repository.CreatureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreaturesViewModel @Inject constructor(creatureRepository: CreatureRepository) : ViewModel() {

    val creatures = MutableLiveData<List<Creature>>()

    init {
        creatures.value = creatureRepository.creatures
    }

}