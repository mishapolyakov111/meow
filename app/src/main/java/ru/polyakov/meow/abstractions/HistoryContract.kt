package ru.polyakov.meow.abstractions

import ru.polyakov.meow.model.entities.CatHistory

interface HistoryContract {
    interface View {
        fun showHistory(cats: List<CatHistory>)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun refreshHistory()
        fun removeHistory(catHistoryId: Long)
    }
}