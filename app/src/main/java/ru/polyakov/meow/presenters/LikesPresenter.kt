package ru.polyakov.meow.presenters

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.polyakov.meow.abstractions.LikesContract
import ru.polyakov.meow.model.repositories.CatRepository

class LikesPresenter : KoinComponent, LikesContract.Presenter {
    private val catRepository: CatRepository by inject()
    private var view: LikesContract.View? = null

    override fun attach(view: LikesContract.View) {
        this.view = view
        refreshLikes()
    }

    override fun detach() {
        view = null
    }

    override fun refreshLikes() {
        val likedCats = catRepository.getAllCats().filter { it.isLiked }
        view?.showLikedCats(likedCats)
    }

    override fun removeCat(statusCode: Int) {
        if (catRepository.removeLikeCat(statusCode) != null) {
            refreshLikes()
        }
    }
}