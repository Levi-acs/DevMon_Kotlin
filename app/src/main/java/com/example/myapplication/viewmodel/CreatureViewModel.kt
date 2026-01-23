package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.domain.Creature
import com.example.myapplication.model.repository.CreaturesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatureViewModel @Inject constructor(private val creaturesRepository: CreaturesRepository) :
    ViewModel() {

    private val _creature = MutableLiveData<Creature>()

    val creature: LiveData<Creature>
        get() = _creature

    fun loadCreature(number: Int) {
        _creature.value = creaturesRepository.findCreature(number)

    }

}