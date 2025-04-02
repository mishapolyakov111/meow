package ru.polyakov.meow.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.polyakov.meow.model.entities.Cat

@Dao
interface CatDao {
    @Query("SELECT * FROM cats WHERE statusCode = :statusCode LIMIT 1")
    fun getCatByCode(statusCode: Int): Cat?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCat(cat: Cat)

    @Update
    fun updateCat(cat: Cat)

    @Query("SELECT * FROM cats")
    fun getAllCats(): List<Cat>
}