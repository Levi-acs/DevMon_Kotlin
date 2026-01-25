package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.extensions.rx.CompositeDisposableExtensions.plusAssign
import com.example.myapplication.model.domain.Creature
import com.example.myapplication.model.repository.CreaturesRepository
import com.example.myapplication.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class CreaturesViewModel @Inject constructor(private val creaturesRepository: CreaturesRepository,
                                           private val  userRepository : UserRepository,) : ViewModel() {

    val creatures = MutableLiveData<List<Creature>>()

    private val composite = CompositeDisposable()

    init {
      composite += userRepository.allCreatures.subscribe {
            creatures.value = it
        }
    }

    override fun onCleared() {
        composite.dispose()
    }


}