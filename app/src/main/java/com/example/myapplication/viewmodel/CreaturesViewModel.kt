package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.extensions.rx.CompositeDisposableExtensions.plusAssign
import com.example.myapplication.model.domain.Creature
import com.example.myapplication.model.repository.CreaturesRepository
import com.example.myapplication.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers // ADICIONE
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers // ADICIONE
import javax.inject.Inject

@HiltViewModel
class CreaturesViewModel @Inject constructor(
    private val creaturesRepository: CreaturesRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    val creatures = MutableLiveData<List<Creature>>()

    private val composite = CompositeDisposable()

    init {

        composite += userRepository.allCreatures
            .subscribeOn(Schedulers.io()) // ADICIONE - Executa em background
            .observeOn(AndroidSchedulers.mainThread()) // ADICIONE - Atualiza UI na thread principal
            .subscribe(
                { creatureList -> // onNext
                    creatures.value = creatureList
                },
                { error -> // onError - ADICIONE tratamento de erro
                    Log.e("CreaturesViewModel", "Error loading creatures", error)
                }
            )
    }

    override fun onCleared() {
        composite.dispose()
    }
}