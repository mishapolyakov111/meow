package ru.polyakov.meow.abstractions

interface MainContract {
    interface View {
        fun showCat(statusCode: Int, imageUrl: String)
        fun showError(message: String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun loadCatByCode(code: String)
        fun likeCat(statusCode: Int)
    }
}