package ru.polyakov.meow.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.polyakov.meow.R
import ru.polyakov.meow.abstractions.MainContract
import ru.polyakov.meow.model.entities.Cat
import ru.polyakov.meow.presenters.MainPresenter

class MainFragment : Fragment(), MainContract.View {
    private lateinit var presenter: MainContract.Presenter
    private lateinit var imageView: ImageView
    private lateinit var likeIconImageView: ImageView
    private lateinit var codeInput: EditText
    private lateinit var loadButton: Button
    private lateinit var likeButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация Views
        imageView = view.findViewById(R.id.catImageView)
        codeInput = view.findViewById(R.id.codeInput)
        loadButton = view.findViewById(R.id.loadButton)
        likeButton = view.findViewById(R.id.likeButton)
        progressBar = view.findViewById(R.id.progressBar)
        errorTextView = view.findViewById(R.id.errorTextView)
        likeIconImageView = view.findViewById(R.id.likeIconImageView)

        // Инициализация презентера
        presenter = MainPresenter()
        presenter.attach(this)

        // Настройка кнопки загрузки
        loadButton.setOnClickListener {
            errorTextView.visibility = View.GONE  // Скрываем ошибку при новой попытке загрузки
            presenter.loadCatByCode(codeInput.text.toString())
        }

        // Настройка кнопки лайка
        likeButton.setOnClickListener {
            presenter.likeCat(codeInput.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    override fun showCat(cat: Cat) {
        likeButton.visibility = View.VISIBLE
        errorTextView.visibility = View.GONE
        likeIconImageView.visibility = if (cat.isLiked) View.VISIBLE else View.GONE

        context?.let {
            Glide.with(it)
                .load(cat.imageUrl)
                .into(imageView)
        }
    }

    override fun showError(message: String) {
        likeButton.visibility = View.GONE
        errorTextView.text = message
        errorTextView.visibility = View.VISIBLE
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        imageView.visibility = View.GONE
        likeButton.visibility = View.GONE
        errorTextView.visibility = View.GONE
        likeIconImageView.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        imageView.visibility = View.VISIBLE
    }

    override fun showLike() {
        likeIconImageView.visibility = View.VISIBLE
    }
}