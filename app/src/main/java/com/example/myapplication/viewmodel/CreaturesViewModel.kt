package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.repository.CreatureRepository
import com.example.myapplication.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreaturesViewModel @Inject constructor( private val creatureRepository: CreatureRepository, userRepository : UserRepository) : ViewModel() {

    val creatures = MutableLiveData(userRepository.allCreatures)

    fun findCreature(number: Int) = creatureRepository.findCreature(number)


}