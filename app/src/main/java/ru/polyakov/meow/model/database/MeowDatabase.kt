package ru.polyakov.meow.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.polyakov.meow.model.dao.CatDao
import ru.polyakov.meow.model.dao.CatHistoryDao
import ru.polyakov.meow.model.entities.Cat
import ru.polyakov.meow.model.entities.CatHistory

@Database(entities = [Cat::class, CatHistory::class], version = 1)
abstract class MeowDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao
    abstract fun catHistoryDao(): CatHistoryDao
}