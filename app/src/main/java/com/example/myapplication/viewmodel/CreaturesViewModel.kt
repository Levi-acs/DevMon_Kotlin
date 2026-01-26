package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.extensions.rx.CompositeDisposableExtensions.plusAssign
import com.example.myapplication.model.domain.Creature
import com.example.myapplication.model.repository.CreaturesRepository
import com.example.myapplication.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CreaturesViewModel @Inject constructor(
    private val creaturesRepository: CreaturesRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    val creatures = MutableLiveData<List<Creature>>()

    private val composite = CompositeDisposable()

    init {
        loadCreatures() // MUDE PARA CHAMAR FUNÇÃO SEPARADA
    }

    // ADICIONE ESTE MÉTODO
    private fun loadCreatures() {
        composite += userRepository.allCreatures
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { creatureList ->
                    Log.d("CreaturesViewModel", "Loaded ${creatureList.size} creatures")
                    creatures.value = creatureList
                },
                { error ->
                    Log.e("CreaturesViewModel", "Error loading creatures", error)
                }
            )
    }

    // ADICIONE ESTE MÉTODO PÚBLICO PARA REFRESH
    fun refreshCreatures() {
        loadCreatures()
    }

    override fun onCleared() {
        composite.dispose()
    }
}