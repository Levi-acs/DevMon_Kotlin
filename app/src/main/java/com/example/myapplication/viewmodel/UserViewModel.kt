package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.extensions.rx.CompositeDisposableExtensions.plusAssign
import com.example.myapplication.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    val user = userRepository.user
    val onChooseCreature = userRepository.onChooseCreature

    private val composite = CompositeDisposable()

    fun chooseCreature() {
        composite += userRepository.chooseCreature()
            .subscribe(
                { creature ->
                    Log.d("CHOOSE_CREATURE", creature.toString())
                },
                { error ->
                    Log.e("CHOOSE_CREATURE", "Erro ao escolher criatura", error)
                }
            )
    }


    override fun onCleared() {
        composite.dispose()
    }
}

