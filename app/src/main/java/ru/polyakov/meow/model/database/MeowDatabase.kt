package ru.polyakov.meow.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.polyakov.meow.model.dao.CatDao
import ru.polyakov.meow.model.entities.Cat

@Database(entities = [Cat::class], version = 1)
abstract class MeowDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao
}