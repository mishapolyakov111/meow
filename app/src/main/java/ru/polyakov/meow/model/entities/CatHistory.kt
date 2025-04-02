package ru.polyakov.meow.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_history")
data class CatHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val statusCode: Int,
    val imageUrl: String,
    val requestTimestamp: Long
)