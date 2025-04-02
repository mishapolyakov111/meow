package ru.polyakov.meow.presenters

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.polyakov.meow.abstractions.MainContract
import ru.polyakov.meow.model.repositories.CatRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.polyakov.meow.model.entities.CatHistory
import ru.polyakov.meow.model.repositories.CatHistoryRepository

class MainPresenter : KoinComponent, MainContract.Presenter {
    private var view: MainContract.View? = null
    private val catRepository: CatRepository by inject()
    private val historyRepository: CatHistoryRepository by inject()

    override fun attach(view: MainContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun loadCatByCode(code: String) {
        view?.showLoading()
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val statusCode = code.toInt()
                val cat = catRepository.getCat(statusCode)
                if (cat != null) {
                    view?.showCat(cat)

                    val catHistory = CatHistory(
                        statusCode = statusCode,
                        imageUrl = cat.imageUrl,
                        requestTimestamp = System.currentTimeMillis()
                    )
                    historyRepository.insertHistory(catHistory)
                } else {
                    view?.showError("Введите корректный HTTP код")
                }
            } catch (e: NumberFormatException) {
                view?.showError("Введите числовой код")
            } finally {
                view?.hideLoading()
            }
        }
    }

    override fun likeCat(code: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val statusCode = code.toInt()
                catRepository.likeCat(statusCode)
                view?.showLike()
            } catch (e: NumberFormatException) {
                view?.showError("Введите числовой код")
            }
        }
    }
}