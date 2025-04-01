package ru.polyakov.meow.model.entities

data class Cat(
    val statusCode: Int,
    val imageUrl: String,
    var isLiked: Boolean = false
)