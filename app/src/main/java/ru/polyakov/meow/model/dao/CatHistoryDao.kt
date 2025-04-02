package ru.polyakov.meow.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.polyakov.meow.model.entities.CatHistory

@Dao
interface CatHistoryDao {
    @Insert
    suspend fun insert(catHistory: CatHistory): Long

    @Query("SELECT * FROM cat_history ORDER BY requestTimestamp DESC")
    suspend fun getAllHistory(): List<CatHistory>

    @Query("DELETE FROM cat_history WHERE id = :id")
    suspend fun deleteHistoryById(id: Long)
}