package ru.polyakov.meow.model.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.polyakov.meow.model.dao.CatHistoryDao
import ru.polyakov.meow.model.entities.CatHistory

class CatHistoryRepository(private val catHistoryDao: CatHistoryDao) {
    suspend fun insertHistory(catHistory: CatHistory): Long = withContext(Dispatchers.IO) {
        catHistoryDao.insert(catHistory)
    }

    suspend fun getAllHistory(): List<CatHistory> = withContext(Dispatchers.IO) {
        catHistoryDao.getAllHistory()
    }

    suspend fun deleteHistoryById(id: Long) = withContext(Dispatchers.IO) {
        catHistoryDao.deleteHistoryById(id)
    }
}