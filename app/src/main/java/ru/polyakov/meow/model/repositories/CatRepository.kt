package ru.polyakov.meow.model.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.polyakov.meow.model.dao.CatDao
import ru.polyakov.meow.model.entities.Cat

class CatRepository(private val catDao: CatDao) {

    private val baseUrl = "https://http.cat/"

    companion object {
        val ALLOWED_HTTP_CODES = intArrayOf(
            100, 101, 102, 103,
            // 2xx Success
            200, 201, 202, 203, 204, 205, 206, 207, 208, 214, 226,
            300, 301, 302, 303, 304, 305, 307, 308,
            400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410,
            411, 412, 413, 414, 415, 416, 417, 418, 419,
            420, 421, 422, 423, 424, 425, 426, 428, 429,
            431,
            444,
            450, 451,
            495, 496, 498, 499,
            500, 501, 502, 503, 504, 506, 507, 508, 509,
            510, 511,
            521, 522, 523, 525,
            530,
            599
        )
    }

    suspend fun getCat(statusCode: Int): Cat? {
        if (statusCode !in ALLOWED_HTTP_CODES) {
            return null
        }
        return withContext(Dispatchers.IO) {
            var cat = catDao.getCatByCode(statusCode)
            if (cat == null) {
                val imageUrl = "$baseUrl$statusCode"
                cat = Cat(statusCode, imageUrl)
                catDao.insertCat(cat)
            }
            cat
        }
    }

    suspend fun likeCat(statusCode: Int): Cat? {
        return withContext(Dispatchers.IO) {
            val cat = getCat(statusCode) ?: return@withContext null
            cat.isLiked = true
            catDao.updateCat(cat)
            cat
        }
    }

    suspend fun removeLikeCat(statusCode: Int): Cat? {
        return withContext(Dispatchers.IO) {
            val cat = getCat(statusCode) ?: return@withContext null
            cat.isLiked = false
            catDao.updateCat(cat)
            cat
        }
    }

    suspend fun getAllCats(): List<Cat> = withContext(Dispatchers.IO) {
        catDao.getAllCats()
    }
}