package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.extensions.rx.CompositeDisposableExtensions.plusAssign
import com.example.myapplication.model.domain.Creature
import com.example.myapplication.model.repository.CreaturesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class CreatureViewModel @Inject constructor(private val creaturesRepository: CreaturesRepository) :
    ViewModel() {

    private val _creature = MutableLiveData<Creature>()

    val creature: LiveData<Creature>
        get() = _creature

    private val composite = CompositeDisposable()

    fun loadCreature(number: Int) {
        composite += creaturesRepository.findCreature(number).subscribe{
            _creature.value = it
        }

    }

    override fun onCleared() {
        composite.dispose()
    }

}