package ru.polyakov.meow.presenters

import ru.polyakov.meow.abstractions.MainContract

class MainPresenter {
    private var view: MainContract.View? = null
    private val baseUrl = "https://http.cat/"


    companion object {
        val ALLOWED_HTTP_CODES = intArrayOf(
            100, 101, 102, 103,
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

    fun attach(view: MainContract.View) {
        this.view = view
    }

    fun detach() {
        view = null
    }

    fun loadCatByCode(code: String) {
        view?.showLoading()

        try {
            val statusCode = code.toInt()
            if (statusCode in ALLOWED_HTTP_CODES) {
                val imageUrl = "$baseUrl$statusCode"
                view?.showCat(statusCode, imageUrl)
            } else {
                view?.showError("Введите корректный HTTP код (100-599)")
            }
        } catch (e: NumberFormatException) {
            view?.showError("Введите числовой код")
        } finally {
            view?.hideLoading()
        }
    }

    fun likeCat(statusCode: Int) {
        // TODO: Сохранение лайкнутого кота в базу данных
    }
}