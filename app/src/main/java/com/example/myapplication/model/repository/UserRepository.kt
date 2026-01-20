package com.example.myapplication.model.repository

import com.example.myapplication.model.domain.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(){
    val user = User("Paulo Salvatore",true)
}