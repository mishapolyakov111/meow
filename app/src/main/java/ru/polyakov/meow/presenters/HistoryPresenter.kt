package ru.polyakov.meow.presenters

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.polyakov.meow.abstractions.HistoryContract
import ru.polyakov.meow.model.repositories.CatHistoryRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HistoryPresenter : HistoryContract.Presenter, KoinComponent {
    private var view: HistoryContract.View? = null
    private val catHistoryRepository: CatHistoryRepository by inject()

    override fun attach(view: HistoryContract.View) {
        this.view = view
        refreshHistory()
    }

    override fun detach() {
        view = null
    }

    override fun refreshHistory() {
        CoroutineScope(Dispatchers.Main).launch {
            val history = catHistoryRepository.getAllHistory()
            view?.showHistory(history)
        }
    }

    override fun removeHistory(catHistoryId: Long) {
        CoroutineScope(Dispatchers.Main).launch {
            catHistoryRepository.deleteHistoryById(catHistoryId)
            refreshHistory()
        }
    }
}