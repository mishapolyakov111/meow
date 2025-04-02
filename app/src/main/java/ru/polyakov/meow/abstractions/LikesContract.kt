package ru.polyakov.meow.abstractions

import ru.polyakov.meow.model.entities.Cat

interface LikesContract {
    interface View {
        fun showLikedCats(cats: List<Cat>)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun refreshLikes()
        fun removeCat(statusCode: Int)
    }
}