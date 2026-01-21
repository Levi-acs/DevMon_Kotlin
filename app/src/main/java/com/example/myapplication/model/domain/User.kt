package com.example.myapplication.model.domain

data class User(
    val name: String,
    var hasCreatureAvailable: Boolean,
    val creatures: MutableList<Creature> = mutableListOf(),
)


