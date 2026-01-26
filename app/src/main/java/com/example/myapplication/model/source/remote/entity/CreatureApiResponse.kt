package com.example.myapplication.model.source.remote.entity

data class CreatureApiResponse(
    val number: Int,
    val name: String,
    val image: String,
    val legendary: Boolean,
    val evolveTo: CreatureApiResponse?,
)