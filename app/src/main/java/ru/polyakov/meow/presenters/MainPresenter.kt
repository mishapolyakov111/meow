package ru.polyakov.meow.presenters

import ru.polyakov.meow.abstractions.MainContract
import ru.polyakov.meow.model.repositories.CatRepository

class MainPresenter : MainContract.Presenter {
    private var view: MainContract.View? = null
    private var catRepository: CatRepository = CatRepository()

    override fun attach(view: MainContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun loadCatByCode(code: String) {
        view?.showLoading()

        try {
            val statusCode = code.toInt()
            val cat = catRepository.getCat(statusCode)
            if (cat != null) {
                view?.showCat(cat)
            } else {
                view?.showError("Введите корректный HTTP код")
            }
        } catch (e: NumberFormatException) {
            view?.showError("Введите числовой код")
        } finally {
            view?.hideLoading()
        }
    }

    override fun likeCat(code: String) {
        try {
            val statusCode = code.toInt()
            catRepository.likeCat(statusCode)
            view?.showLike()
        } catch (e: NumberFormatException) {
            view?.showError("Введите числовой код")
        }
    }
}