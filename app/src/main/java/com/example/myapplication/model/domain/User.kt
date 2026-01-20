package com.example.myapplication.model.domain

data class User(
    val name: String,
    val hasCreatureAvailable: Boolean,
    val creatures: List<Creature> = emptyList(),
)


