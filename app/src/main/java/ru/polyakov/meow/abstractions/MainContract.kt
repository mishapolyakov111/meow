package ru.polyakov.meow.abstractions

import ru.polyakov.meow.model.entities.Cat

interface MainContract {
    interface View {
        fun showCat(cat: Cat)
        fun showError(message: String)
        fun showLoading()
        fun hideLoading()
        fun showLike()
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun loadCatByCode(code: String)
        fun likeCat(code: String)
    }
}