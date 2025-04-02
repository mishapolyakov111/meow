package ru.polyakov.meow.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.polyakov.meow.R
import ru.polyakov.meow.abstractions.LikesContract
import ru.polyakov.meow.model.entities.Cat
import ru.polyakov.meow.presenters.LikesPresenter

class LikesFragment : Fragment(), LikesContract.View {
    private lateinit var presenter: LikesContract.Presenter
    private lateinit var container: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_likes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        container = view.findViewById(R.id.likedCatsContainer)

        presenter = LikesPresenter()
        presenter.attach(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.refreshLikes()
    }


    override fun showLikedCats(cats: List<Cat>) {
        container.removeAllViews()
        val inflater = LayoutInflater.from(context)

        cats.forEach { cat ->
            val itemView = inflater.inflate(R.layout.item_liked_cat, container, false)
            val imageView = itemView.findViewById<ImageView>(R.id.likedCatImageView)
            val removeButton = itemView.findViewById<Button>(R.id.removeCatButton)

            Glide.with(requireContext())
                .load(cat.imageUrl)
                .into(imageView)

            removeButton.setOnClickListener {
                presenter.removeCat(cat.statusCode)
            }

            container.addView(itemView)
        }
    }
}