package ru.polyakov.meow.model.repositories

import ru.polyakov.meow.model.entities.Cat

class CatRepository {
    private val baseUrl = "https://http.cat/"

    companion object {
        val ALLOWED_HTTP_CODES = intArrayOf(
            // 1xx Informational
            100, 101, 102, 103,
            // 2xx Success
            200, 201, 202, 203, 204, 205, 206, 207, 208, 214, 226,
            // 3xx Redirection
            300, 301, 302, 303, 304, 305, 307, 308,
            // 4xx Client Error
            400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410,
            411, 412, 413, 414, 415, 416, 417, 418, 419,
            420, 421, 422, 423, 424, 425, 426, 428, 429,
            431,
            444,
            450, 451,
            495, 496, 498, 499,
            // 5xx Server Error
            500, 501, 502, 503, 504, 506, 507, 508, 509,
            510, 511,
            521, 522, 523, 525,
            530,
            599
        )
    }

    private val cats: MutableMap<Int, Cat> = mutableMapOf()

    fun getCat(statusCode: Int): Cat? {
        if (statusCode !in ALLOWED_HTTP_CODES) {
            return null
        }
        return cats.getOrPut(statusCode) {
            val imageUrl = "$baseUrl$statusCode"
            Cat(statusCode, imageUrl)
        }
    }

    fun likeCat(statusCode: Int): Cat? {
        val cat = getCat(statusCode) ?: return null
        cat.isLiked = true
        return cat
    }

    fun getAllCats(): List<Cat> {
        return cats.values.toList()
    }
}