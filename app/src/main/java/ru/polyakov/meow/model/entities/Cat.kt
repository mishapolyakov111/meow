package ru.polyakov.meow.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class Cat(
    @PrimaryKey val statusCode: Int,
    val imageUrl: String,
    var isLiked: Boolean = false
)